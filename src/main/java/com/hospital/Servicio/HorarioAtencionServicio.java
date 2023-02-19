package com.hospital.Servicio;


import com.hospital.Genericos.IMedicosHorarios;
import com.hospital.Repositorio.HorarioAtencionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.Modelo.HorarioAtencion;
import com.hospital.Modelo.Persona;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioAtencionServicio {

    @Autowired
    HorarioAtencionRepositorio horarioAtencionRepo;

    @Autowired
    ConfigurarHorarioServicio configurarHorarioServicio;

    @Autowired
    PersonaServicio personaServicio;

    public HorarioAtencion insertar(HorarioAtencion horarioAtencion) {
        return horarioAtencionRepo.save(horarioAtencion);
    }

    public HorarioAtencion actualizar(HorarioAtencion horarioAtencion) {
        return horarioAtencionRepo.save(horarioAtencion);
    }

    public List<HorarioAtencion> listar() {
        return horarioAtencionRepo.findAll();
    }


    public HorarioAtencion buscarHorarioPorId(Long id) {
        var buscaEspe = horarioAtencionRepo.findById(id);

        if (buscaEspe.isPresent()) {
            return buscaEspe.get();
        }
        return null;
    }

    public  void actualizaEstadoHorario(Long id, Boolean asigna){
        var buscaHorario = horarioAtencionRepo.findById(id);
        if(buscaHorario.isPresent()){
            buscaHorario.get().setAsignado(asigna);
        }
        horarioAtencionRepo.save(buscaHorario.get());
    }

    public  void cambiarEstadoHorarioPorId(Long id, Boolean estado){
        var buscaHorario = horarioAtencionRepo.findById(id);
        if(buscaHorario.isPresent()){
            buscaHorario.get().setActivo(estado);
        }
        horarioAtencionRepo.save(buscaHorario.get());
    }


    public Long count() {
        return horarioAtencionRepo.count();

    }

    public List<HorarioAtencion> generarHorarios() {
        var cGuardada = configurarHorarioServicio.listar();
        List<HorarioAtencion> listaHorarios = new ArrayList<HorarioAtencion>();

        //Para el tema de los horarios
        var horaDia = cGuardada.get(0).getHora_inicio_dia();
        var finDia = cGuardada.get(0).getHora_fin_dia();


        var horaTarde = cGuardada.get(0).getHora_inicio_tarde();
        var finTarde = cGuardada.get(0).getHora_fin_tarde();
        var tiempoCita = cGuardada.get(0).getDuracion_cita();

        var minutos = tiempoCita.getMinute();
        finDia = finDia.plusMinutes(minutos);
        finTarde = finTarde.plusMinutes(minutos);

        //Para el tema del calculo de la fecha
        var inicioSemana = cGuardada.get(0).getDia_inicio_semana();
        var FinSemana = cGuardada.get(0).getDia_fin_semana();
        var DiasPlanificacion = cGuardada.get(0).getDias_planificacion();

        //Validar si ya existe una planificacion ingresada

        LocalDate fechaActual = null;

        var todosHorarios = listar();
        var ultimaFecha = horarioAtencionRepo.buscarUltimaFechaHorarioAtencion();

        if (todosHorarios.size() >0 ){
            //Obtener Asignamos la ultima fecha mas 1 dia
            var fecha = ultimaFecha.get(0).getFecha_dia().toString();

            fechaActual =  LocalDate.parse(fecha).plusDays(1);

        }else{
            //Obtener fecha del dia
            fechaActual = LocalDate.now();

        }

        //Inicializar contador para recorrer y validar la fecha
        int contador = 0;

        while (contador < DiasPlanificacion) {
            var diaHoy = fechaActual.getDayOfWeek().getValue();
            if (diaHoy >= inicioSemana && diaHoy <= FinSemana) {
                contador++;

                while (horaDia.isBefore(finDia)) {
                    listaHorarios.add(new HorarioAtencion(fechaActual, horaDia, true, false));

                    horaDia = horaDia.plusMinutes(minutos);

                }
                while (horaTarde.isBefore(finTarde)) {
                    listaHorarios.add(new HorarioAtencion(fechaActual, horaTarde, true, false));

                    horaTarde = horaTarde.plusMinutes(minutos);

                }

            }

            //Reinicio el valor de las horas a sus valores iniciales
            horaDia = cGuardada.get(0).getHora_inicio_dia();
            horaTarde = cGuardada.get(0).getHora_inicio_tarde();
            //Aumento el valor de la fecha actual
            fechaActual = fechaActual.plusDays(1);

        }

        return listaHorarios;

    }

    public List<IMedicosHorarios> buscarMedicosConHorarios() {
        return horarioAtencionRepo.buscarMedicosHorarios();
    }

    public List<HorarioAtencion> buscaHorariosEspecialidad(Long id) {

        List<Persona> medicos = personaServicio.listarPersonasPorEspecialidad(id);

        List<HorarioAtencion> horarios = new ArrayList<HorarioAtencion>();

        for (Persona persona : medicos) {
            horarios.addAll(persona.getHorarios());

        }
        return horarios;

    }


    public List<IMedicosHorarios> buscaDiasLibresPorEspecialidad(Long espeId) {
        return horarioAtencionRepo.diaslibresPorEspecialidad(espeId);

    }

    public List<IMedicosHorarios> buscaUltimaFechaHorarioAtencion() {
        return horarioAtencionRepo.buscarUltimaFechaHorarioAtencion();

    }

    public List<IMedicosHorarios> buscaMedicosPorEspecialidadFechaDia(Long espeId, String fecha_dia) {
        LocalDate fecha = LocalDate.parse(fecha_dia);

        return horarioAtencionRepo.medicosPorEspecialidadFechaDia(espeId, fecha);

    }

    public List<IMedicosHorarios> buscaHorariosPorEspecialidadFechaDiaMedico(Long espeId, String fecha_dia, Long idMedico) {
        LocalDate fecha = LocalDate.parse(fecha_dia);

        LocalDate hoy = LocalDate.now();

        if(hoy.isEqual(fecha)){
            return horarioAtencionRepo.horariosPorEspecialidadFechaActualMedico(espeId, fecha, idMedico);
        }
        return horarioAtencionRepo.horariosPorEspecialidadFechaSuperiorMedico(espeId, fecha, idMedico);


    }

}

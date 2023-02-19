package com.hospital.Servicio;

import com.hospital.Genericos.ICitasMedicas;
import com.hospital.Modelo.CitaMedica;
import com.hospital.Repositorio.CitaMedicaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CitaMedicaServicio {

    @Autowired
    CitaMedicaRepositorio citaMedicaRepo;

    public CitaMedica insertar(CitaMedica citaMedica) {
        citaMedica.setAtencion(false);
        citaMedica.setEstado(true);



        return citaMedicaRepo.save(citaMedica);
    }

    public CitaMedica actualizar(CitaMedica citaMedica) {
        return citaMedicaRepo.save(citaMedica);
    }

    public List<CitaMedica> listar() {
        return citaMedicaRepo.findAll();
    }

    public void eliminarCitaMedica(CitaMedica citaMedica) {
        citaMedica.setEstado(false);
        citaMedicaRepo.save(citaMedica);

    }


    public void eliminarCitaMedicaPorId(Long id) {
        var buscaEspe = buscarCitaMedicaPorId(id);
        buscaEspe.setEstado(false);
        citaMedicaRepo.save(buscaEspe);

    }


    public CitaMedica buscarCitaMedicaPorId(Long id) {
        var buscaEspe = citaMedicaRepo.findById(id);

        if (buscaEspe.isPresent()) {
            return buscaEspe.get();
        }
        return null;
    }


    public Long count() {
        return citaMedicaRepo.count();

    }

    public List<CitaMedica> buscaCitaMedicaPorEstado(Boolean estado) {
        var listaEspe = citaMedicaRepo.findAllByEstado(estado);
        if (listaEspe.isEmpty()) {
            return null;
        }
        return listaEspe;
    }

    public List<ICitasMedicas> listadoCitasPorAtencion(Boolean estadoAtencion) {
        return citaMedicaRepo.listadoCitasPorAtencion(estadoAtencion);
    }

    public List<ICitasMedicas> listadoCitasAgendadas() {
        return citaMedicaRepo.listadoCitasAgendadas();
    }


    public List<ICitasMedicas> listadoCitasPorMedicoAtencion(Long medico , Boolean estadoAtencion) {
        return citaMedicaRepo.listadoCitasPorMedicoAtencion(medico, estadoAtencion);
    }

    public List<ICitasMedicas> listadoCitasPorPaciente(Long persona , Boolean estadoAtencion) {
        return citaMedicaRepo.listadoCitasPorPaciente(persona, estadoAtencion);
    }

    public List<ICitasMedicas> listadoCitasAtendidasPorEspecialidad() {
        return citaMedicaRepo.listadoCitasAtendidasPorEspecialidad();
    }

    public List<ICitasMedicas> listadoCitasAtendidasPorMedico() {
        return citaMedicaRepo.listadoCitasAtendidasPorMedico();
    }

    public List<ICitasMedicas> listadoCitasAtendidasPorMes() {
        return citaMedicaRepo.listadoCitasAtendidasPorMes();
    }

    public List<ICitasMedicas> listadoCitasAgendadasPorEspecialidad() {
        return citaMedicaRepo.listadoCitasAgendadasPorEspecialidad();
    }

    public List<ICitasMedicas> listadoCitasAgendadasPorMedico() {
        return citaMedicaRepo.listadoCitasAgendadasPorMedico();
    }

    public List<ICitasMedicas> buscaCitasPacienteEspecialidadDia(Long idpersona , Long idEspecialidad, LocalDate fecha){
        return citaMedicaRepo.buscaCitasPacienteEspecialidadDia(idpersona,idEspecialidad,fecha);
    }

    public List<ICitasMedicas> buscaCitasPacientePorDia(Long idpersona ,  LocalDate fecha){
        return citaMedicaRepo.buscaCitasPacientePorDia(idpersona,fecha);
    }


    public List<ICitasMedicas> listadoCitasAgendadasPorMes() {
        return citaMedicaRepo.listadoCitasAgendadasPorMes();
    }


    public void cancelarCita(Long id) {

        var cita = citaMedicaRepo.findById(id);

        if (cita.isPresent()) {
            cita.get().setEstado(false);
            citaMedicaRepo.save(cita.get());
        }

    }

    public List<CitaMedica> listarTodasCitas(){
        citaMedicaRepo.findAll().forEach(x ->{
            System.out.println(x.getAtencion() + x.getAtencionMedica().getSintomas());
        });
        return citaMedicaRepo.findAll();
    }

    public List <CitaMedica> buscarCitaporId_Horario(Long id){
        List <CitaMedica> cita = new ArrayList<CitaMedica>();
        cita = citaMedicaRepo.buscarCitaPorId_Horario(id);

        if(cita != null){
            return cita;
        }
        return null;
    }

    public Integer totalCitasHoy(){
        return citaMedicaRepo.totalCitasHoy();
    }

    public Integer totalCitasAyer(){
        return citaMedicaRepo.totalCitasAyer();
    }

    public Integer totalCitasMesActual(){
        return citaMedicaRepo.totalCitasMesActual();
    }

    public Integer totalCitasMesAnterior(){
        return citaMedicaRepo.totalCitasMesAnterior();
    }

    public Integer totalCitasAnioAactual(){
        return citaMedicaRepo.totalCitasAnioActual();
    }

    public Integer totalCitasAnioAnterior(){
        return citaMedicaRepo.totalCitasAnioAnterior();
    }

    public List<ICitasMedicas> totalCitasPorMes(){
        return citaMedicaRepo.totalCitasPorMes();
    }

    public List<ICitasMedicas> citasPorEspecialidadMesActual(){
        return citaMedicaRepo.citasAgendadasPorEspecialidadMesActual();
    }
}

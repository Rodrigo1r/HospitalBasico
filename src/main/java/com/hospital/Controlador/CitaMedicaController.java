package com.hospital.Controlador;


import com.hospital.Genericos.ICitasMedicas;
import com.hospital.Genericos.IMedicosHorarios;
import com.hospital.Modelo.CitaMedica;
import com.hospital.Modelo.HorarioAtencion;
import com.hospital.Modelo.Persona;
import com.hospital.Servicio.CitaMedicaServicio;
import com.hospital.Servicio.EspecialidadServicio;
import com.hospital.Servicio.HorarioAtencionServicio;
import com.hospital.Servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cita")
public class CitaMedicaController {

    @Autowired
    EspecialidadServicio especialidadSvc;

    @Autowired
    PersonaServicio personaSvc;

    @Autowired
    HorarioAtencionServicio horarioAtencionSvc;

    @Autowired
    CitaMedicaServicio citaMedicaSvc;

    @GetMapping("/listar")
    public String listado(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        var lista = citaMedicaSvc.listadoCitasPorAtencion(false);

        model.addAttribute("lista",lista );
        return "cita/form-lista-citas";
    }


    @GetMapping("/listaPaciente")
    public String listaPaciente(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Persona persona = new Persona();
        if(auth !=null){
            persona = personaSvc.buscaPersonaPorUsuario(auth.getName());
        }

        var lista = citaMedicaSvc.listadoCitasPorPaciente(persona.getId(),false);
        model.addAttribute("lista", lista);
        return "cita/lista-citas-paciente";
    }

    @GetMapping("/crear")
    public String llamaFormCita(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("paciente", null);
        return "cita/form-nueva-cita";
    }

    @GetMapping("/paciente")
    public String llamaFormCitaPaciente(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        Persona persona = buscaUsuarioLogueado();

        model.addAttribute("cita", new CitaMedica());
        model.addAttribute("especialidades", especialidadSvc.buscaEspecialidadPorEstado(true));
        model.addAttribute("paciente", persona);

        return "cita/form-cita-paciente";
    }

    public Persona buscaUsuarioLogueado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Persona persona = new Persona();
        if(auth !=null){
            persona = personaSvc.buscaPersonaPorUsuario(auth.getName());
            return  persona;
        }
        return null;

    }

    @PostMapping("/paciente")
    public String crearCitaPaciente(CitaMedica cita, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        var medico = personaSvc.buscaPersonaPorId(cita.getId_medico());
        cita.setNombreMedico(medico.getNombres() + " " + medico.getApellidos());

        Persona paciente = buscaUsuarioLogueado();
        var fechaDia = horarioAtencionSvc.buscarHorarioPorId(cita.getId_horario());
        var listaCitas = citaMedicaSvc.buscaCitasPacienteEspecialidadDia(cita.getId_persona(),cita.getId_especialidad(), fechaDia.getFecha_dia());
        var citasDia = citaMedicaSvc.buscaCitasPacientePorDia(cita.getId_persona(), fechaDia.getFecha_dia());

        if(listaCitas.size() > 0 ){
            model.addAttribute("especialidades", especialidadSvc.buscaEspecialidadPorEstado(true));
            model.addAttribute("paciente", paciente);
            model.addAttribute("citaExiste","Ya existe un registro de citas con estos datos para este día");
            model.addAttribute("cita", new CitaMedica());
            return "cita/form-cita-paciente";

        }else if(citasDia.size() == 2){
            model.addAttribute("especialidades", especialidadSvc.buscaEspecialidadPorEstado(true));
            model.addAttribute("paciente", paciente);
            model.addAttribute("citaExiste","El máximo de citas que puede agendar en el día es 2");
            model.addAttribute("cita", new CitaMedica());
            return "cita/form-cita-paciente";

        }else{
            citaMedicaSvc.insertar(cita);
            horarioAtencionSvc.actualizaEstadoHorario(cita.getId_horario(),true);

        }

        return "redirect:/cita/listaPaciente";
    }

    @PostMapping("/buscarPaciente")
    public String buscarPaciente(@RequestParam(value = "identificacion",
            required = false) String identificacion, Model model,
                                 HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("cita", new CitaMedica());
        var paciente = personaSvc.buscarPersonaPorIdentificacion(identificacion);
        if(paciente == null){
            model.addAttribute("pExiste", "No existen datos con la identificación ingresada");
        }
        model.addAttribute("paciente", paciente);

        model.addAttribute("especialidades", especialidadSvc.buscaEspecialidadPorEstado(true));
        return "cita/form-nueva-cita";
    }

    @PostMapping("/crear")
    public String crearCita(CitaMedica cita, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        var medico = personaSvc.buscaPersonaPorId(cita.getId_medico());
        cita.setNombreMedico(medico.getNombres() + " " + medico.getApellidos());

        Persona paciente = buscaUsuarioLogueado();
        model.addAttribute("especialidades", especialidadSvc.buscaEspecialidadPorEstado(true));
        model.addAttribute("paciente", paciente);
        model.addAttribute("cita", new CitaMedica());

        var fechaDia = horarioAtencionSvc.buscarHorarioPorId(cita.getId_horario());
        var listaCitas = citaMedicaSvc.buscaCitasPacienteEspecialidadDia(cita.getId_persona(),cita.getId_especialidad(), fechaDia.getFecha_dia());
        var citasDia = citaMedicaSvc.buscaCitasPacientePorDia(cita.getId_persona(), fechaDia.getFecha_dia());

        if(listaCitas.size() > 0 ){
            model.addAttribute("citaExiste","Ya existe un registro de citas con estos datos para este día");
            return "cita/form-nueva-cita";

        }else if(citasDia.size() == 2){
            model.addAttribute("citaExiste","El máximo de citas que puede agendar en el día es 2");
            return "cita/form-nueva-cita";

        }else{
            citaMedicaSvc.insertar(cita);
            horarioAtencionSvc.actualizaEstadoHorario(cita.getId_horario(),true);

        }

        return "redirect:/cita/listar";
    }


    @RequestMapping(value = "/cargaEspecialidadesDias", method = RequestMethod.GET)
    public @ResponseBody List<HorarioAtencion> cargaEspecialidadesDias(@RequestParam(value = "espeId", required = true) Long espeId) {

        var listaDias = horarioAtencionSvc.buscaHorariosEspecialidad(espeId);

        return listaDias;

    }

    @RequestMapping(value = "/cargaDiasLibresPorEspecialidad", method = RequestMethod.GET)
    public @ResponseBody List<IMedicosHorarios> cargaDiasLibresPorEspecialidad(@RequestParam(value = "espeId", required = true) Long espeId) {

        var listaDias = horarioAtencionSvc.buscaDiasLibresPorEspecialidad(espeId);

        return listaDias;

    }

    @RequestMapping(value = "/cargaMedicosPorEspecialidad", method = RequestMethod.GET)
    public @ResponseBody List<IMedicosHorarios> cargaMedicosPorEspecialidad(@RequestParam(value = "espeId", required = true) Long espeId,
                                                                            @RequestParam(value = "fechaDia", required = true) String fechaDia) {
        var listaMedicos = horarioAtencionSvc.buscaMedicosPorEspecialidadFechaDia(espeId, fechaDia);

        return listaMedicos;
    }

    @RequestMapping(value = "/cargahorariosPorEspecialidadFechaDiaMedico", method = RequestMethod.GET)
    public @ResponseBody List<IMedicosHorarios> cargahorariosPorEspecialidadFechaDiaMedico(@RequestParam(value = "espeId", required = true) Long espeId,
                                                                                           @RequestParam(value = "fechaDia", required = true) String fechaDia,
                                                                                           @RequestParam(value = "idMedico", required = true) Long idMedico) {

        var listaHorarios = horarioAtencionSvc.buscaHorariosPorEspecialidadFechaDiaMedico(espeId, fechaDia, idMedico);

        return listaHorarios;
    }
    @RequestMapping(value = "/buscarCitaPersonaEspecialidadFecha", method = RequestMethod.GET)
    public @ResponseBody List<ICitasMedicas> cargaCitaPersonaEspecialidadFecha(@RequestParam(value = "idPersona", required = true) Long idPersona,
                                                                               @RequestParam(value = "idEspecialidad", required = true) Long idEspecialidad,
                                                                               @RequestParam(value = "idHorario", required = true) Long idHorario) {


        var fechaDia = horarioAtencionSvc.buscarHorarioPorId(idHorario);

        var listaHorarios = citaMedicaSvc.buscaCitasPacienteEspecialidadDia(idPersona,idEspecialidad, fechaDia.getFecha_dia());

        return listaHorarios;
    }


    @RequestMapping(value = "/agendarCita", method = RequestMethod.GET)
    public @ResponseBody String agendarCita(@RequestParam(value = "idPersona", required = true) Long idPersona,
                                            @RequestParam(value = "idEspecialidad", required = true) Long idEspecialidad,
                                            @RequestParam(value = "idMedico", required = true) Long idMedico,
                                            @RequestParam(value = "nombreMedico", required = true) String nombreMedico,
                                            @RequestParam(value = "idHorario", required = true) Long idHorario , Model model)  {

        CitaMedica citaMedica = new CitaMedica(idPersona, idMedico, nombreMedico, idHorario, idEspecialidad);

        var fechaDia = horarioAtencionSvc.buscarHorarioPorId(idHorario);
        var listaHorarios = citaMedicaSvc.buscaCitasPacienteEspecialidadDia(idPersona,idEspecialidad, fechaDia.getFecha_dia());

        if(listaHorarios != null){
            model.addAttribute("citaExiste","Ya existe un registro de citas con estos datos");
            return "cita/form-nueva-cita";

        }else{
            citaMedicaSvc.insertar(citaMedica);
            horarioAtencionSvc.actualizaEstadoHorario(idHorario,true);
        }

        return "redirect:/cita/listar";
    }

    @RequestMapping(value = "/agendarCitaPaciente", method = RequestMethod.GET)
    public @ResponseBody String agendarCitaPaciente(@RequestParam(value = "idPersona", required = true) Long idPersona,
                                            @RequestParam(value = "idEspecialidad", required = true) Long idEspecialidad,
                                            @RequestParam(value = "idMedico", required = true) Long idMedico,
                                            @RequestParam(value = "nombreMedico", required = true) String nombreMedico,
                                            @RequestParam(value = "idHorario", required = true) Long idHorario) {

        CitaMedica citaMedica = new CitaMedica(idPersona, idMedico, nombreMedico, idHorario, idEspecialidad);
        citaMedicaSvc.insertar(citaMedica);
        horarioAtencionSvc.actualizaEstadoHorario(idHorario,true);


        return "redirect:/cita/listaPaciente";
    }


    @GetMapping("/cancelarCita/{id}")
    public String cancelarCita(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));


        var cita = citaMedicaSvc.buscarCitaMedicaPorId(id);
        horarioAtencionSvc.actualizaEstadoHorario(cita.getId_horario(), false);
        citaMedicaSvc.cancelarCita(id);


        return "redirect:/cita/listar";
    }

    @RequestMapping(value = "/cancelaCitas", method = RequestMethod.GET)
    public @ResponseBody String cancelaCitas(@RequestParam(value = "idCita", required = true) Long id) {

        citaMedicaSvc.cancelarCita(id);
        var cita = citaMedicaSvc.buscarCitaMedicaPorId(id);
        horarioAtencionSvc.actualizaEstadoHorario(cita.getId_horario(), false);

        return "redirect:/cita/listar";
    }


    @RequestMapping(value = "/buscaCitaPorIdHorario", method = RequestMethod.GET)
    public @ResponseBody List <CitaMedica> validaCitasHorario(@RequestParam(value = "idHorario", required = true) Long id) {

        return citaMedicaSvc.buscarCitaporId_Horario(id);

    }



}
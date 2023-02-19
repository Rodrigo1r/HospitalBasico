package com.hospital.Controlador;

import com.hospital.Modelo.AtencionMedica;
import com.hospital.Modelo.Persona;
import com.hospital.Servicio.AtencionMedicaServicio;
import com.hospital.Servicio.CitaMedicaServicio;
import com.hospital.Servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/atencion")
public class AtencionMedicaController {

    @Autowired
    AtencionMedicaServicio atencionMedicaSvc;

    @Autowired
    CitaMedicaServicio citaMedicaSvc;

    @Autowired
    PersonaServicio personaSvc;


    @GetMapping("/listar")
    public String listado(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Persona persona = new Persona();
        if(auth !=null){
            persona = personaSvc.buscaPersonaPorUsuario(auth.getName());
        }

        var lista = citaMedicaSvc.listadoCitasPorMedicoAtencion(persona.getId(),false);
        model.addAttribute("lista", lista);
        return "atencion/form-lista-atenciones";
    }

    @GetMapping("/listarAtendidos")
    public String listadoAtendidos(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Persona persona = new Persona();
        if(auth !=null){
            persona = personaSvc.buscaPersonaPorUsuario(auth.getName());
        }

        var lista = citaMedicaSvc.listadoCitasPorMedicoAtencion(persona.getId(),true);
        model.addAttribute("lista", lista);
        return "atencion/form-lista-atendidos";
    }

    @GetMapping("/registrar/{id}")
    public String editarPersona(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        var cita = citaMedicaSvc.buscarCitaMedicaPorId(id);
        var motivos =atencionMedicaSvc.motivosConsulta();

        var persona = personaSvc.buscaPersonaPorId(cita.getId_persona());
        model.addAttribute("paciente", persona);
        model.addAttribute("motivos", motivos);
        model.addAttribute("atencion", new AtencionMedica());

        return "atencion/form-registrar-atencion";
    }


    @PostMapping("/registrar/{id}")
    public String actualizarPersona(@PathVariable long id, AtencionMedica atencion, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));


        var cita = citaMedicaSvc.buscarCitaMedicaPorId(id);
        cita.setAtencionMedica(atencion);
        cita.setAtencion(true);
        //atencion.setFechaAtencion(LocalDate.now());
        atencion.setCitaMedica(cita);
        citaMedicaSvc.actualizar(cita);

        return "redirect:/atencion/listar";

    }


}

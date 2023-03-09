package com.hospital.Controlador;


import com.hospital.Modelo.Especialidad;
import com.hospital.Modelo.Examen;
import com.hospital.Servicio.CitaMedicaServicio;
import com.hospital.Servicio.ExamenServicio;
import com.hospital.Servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/examen")
public class ExamenController {

    @Autowired
    ExamenServicio examenSvc;

    @Autowired
    CitaMedicaServicio citaMedicaSvc;
    @Autowired
    PersonaServicio personaSvc;

    @GetMapping("/registrar/{id}")
    public String nuevoExamen(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        var cita = citaMedicaSvc.buscarCitaMedicaPorId(id);
        var persona = personaSvc.buscaPersonaPorId(cita.getId_persona());
        var motivos = examenSvc.motivosExamen();
        Examen nuevo = new Examen();
        nuevo.setAtencion(cita.getAtencionMedica());
        model.addAttribute("paciente", persona);
        model.addAttribute("motivos", motivos);
        model.addAttribute("examen", nuevo);
        return "atencion/examen/form-nuevo-examen";
    }

    @PostMapping("/registrar/{id}")
    public String registroExamen(@PathVariable long id,Examen examen, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        //Primero valido que la especialidad no exista, porque debe ser uncica

        var cita = citaMedicaSvc.buscarCitaMedicaPorId(id);
        examen.setAtencion(cita.getAtencionMedica());
            examenSvc.insertar(examen);
            return "redirect:/atencion/listarAtendidos";
    }

}

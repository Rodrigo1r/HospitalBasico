package com.hospital.Controlador;

import com.hospital.Modelo.AtencionMedica;
import com.hospital.Modelo.DetalleClinico;
import com.hospital.Modelo.HistoriaClinica;
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
import java.util.ArrayList;
import java.util.List;

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


        atencion.setCitaMedica(cita);
        citaMedicaSvc.actualizar(cita);

        //Agregamos nuevo detalle clinico que basicamente es el Id de la atencion medica
        var atencionMd = atencionMedicaSvc.buscaAtencionPorIdCIta(id);
        DetalleClinico dt = new DetalleClinico();
        dt.setIdAtencion(atencionMd.getId());

        //Si el paciente no tiene historia clinica, entocnes le creo una nueva y le asigno el detalle clinico
        //Pero si el paciente ya cuenta con uan historia clinica, simplemente le agrego el nuevo detalle clinico
        var persona =personaSvc.buscaPersonaPorId(cita.getId_persona());
        List<DetalleClinico> ldc = new ArrayList<DetalleClinico>();
        if(persona.getHistoriaClinica() == null){
            HistoriaClinica hc = new HistoriaClinica();
            //dt.setHistoria(hc);
            ldc.add(dt);
            hc.setDetalles(ldc);
            //hc.setPersona(persona);

            persona.setHistoriaClinica(hc);

        }else{
            ldc= persona.getHistoriaClinica().getDetalles();
            ldc.add(dt);
            persona.getHistoriaClinica().setDetalles(ldc);
        }

        personaSvc.actualizarPersona(persona);


        return "redirect:/atencion/listar";

    }


}

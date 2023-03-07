package com.hospital.Controlador;

import com.hospital.Modelo.AtencionMedica;
import com.hospital.Modelo.DetalleClinico;
import com.hospital.Modelo.HistoriaClinica;
import com.hospital.Modelo.Persona;
import com.hospital.Servicio.AtencionMedicaServicio;
import com.hospital.Servicio.CitaMedicaServicio;
import com.hospital.Servicio.HistoriaClinicaServicio;
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

    @Autowired
    HistoriaClinicaServicio historiaSvc;


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
        DetalleClinico detClin = new DetalleClinico();
        detClin.setIdAtencion(atencionMd.getId());

        //Si el paciente no tiene historia clinica, entocnes le creo una nueva y le asigno el detalle clinico
        //Pero si el paciente ya cuenta con uan historia clinica, simplemente le agrego el nuevo detalle clinico
        var persona =personaSvc.buscaPersonaPorId(cita.getId_persona());
        List<DetalleClinico> ldc = new ArrayList<DetalleClinico>();
        HistoriaClinica hc = new HistoriaClinica();
        
        if(persona.getHistoria() == null){
            detClin.setHistoria(hc);
            //Esto agregue
            detClin.setAtencion(atencion);

            ldc.add(detClin);
            hc.setDetalle(ldc);
            hc.setPersona(persona);
            persona.setHistoria(hc);

        }else{
            //Caso contrario, significa que este paciente ya cuenta con historia clinica
            //Obtenemos sus historia clinaca actual, y sobre esta trabajamos
            hc = persona.getHistoria();
            ldc= persona.getHistoria().getDetalle();
            detClin.setHistoria(persona.getHistoria());
            //Esto agregue
            detClin.setAtencion(atencion);


            ldc.add(detClin);
            hc.setDetalle(ldc);
            
        }

        //Por ultimo guardamos la historia clinica, que en cascada actualiza la persona y el detalle
        historiaSvc.grabar(hc);


        return "redirect:/atencion/listar";

    }

    @GetMapping("/historia/{id}")
    public String historial(@PathVariable long id,Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        var paciente = personaSvc.buscaPersonaPorId(id);



        var lista = paciente.getHistoria().getDetalle();
        model.addAttribute("lista", lista);
        return "atencion/lista-historial-clinico";
    }


}

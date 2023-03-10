package com.hospital.Controlador;

import com.hospital.Modelo.Enfermedad;
import com.hospital.Modelo.Especialidad;
import com.hospital.Modelo.Persona;
import com.hospital.Servicio.EnfermedadServicio;
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
@RequestMapping("/enfermedad")
public class EnfermedadController {

    @Autowired
    EnfermedadServicio enfermedadSvc;

    @Autowired
    PersonaServicio personaSvc;

    @GetMapping("/listar")
    public String listado(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        var paciente = buscaUsuarioLogueado();
        var lista = personaSvc.buscaPersonaPorId(paciente.getId());



        model.addAttribute("lista",lista.getEnfermedad() );
        return "persona/enfermedades/lista-enfermedades";
    }

    @GetMapping("/agregar")
    public String agregarEnfermedad(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        var paciente = buscaUsuarioLogueado();
        model.addAttribute("paciente",paciente );
        model.addAttribute("enfermedad",new Enfermedad() );
        return "persona/enfermedades/form-nueva-enfermedad";
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

    @PostMapping("/agregar")
    public String grabarEnfermedad(Enfermedad enfermedad, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        var paciente = buscaUsuarioLogueado();

        enfermedad.setPersona(paciente);

        enfermedadSvc.insertar(enfermedad);
        return "redirect:/enfermedad/listar";
    }


    @GetMapping("/editar/{id}")
    public String editarEnfermedad(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        var enfermedad = enfermedadSvc.buscarEnfermedadPorId(id);
        model.addAttribute("enfermedad", enfermedad);
        return "persona/enfermedades/form-editar-enfermedad";
    }

    @PostMapping("/editar/{id}")
    public String actualizarPersona(@PathVariable long id, Enfermedad enfermedad, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        var paciente = buscaUsuarioLogueado();
        enfermedad.setPersona(paciente);
        enfermedadSvc.actualizar(enfermedad);
        return "redirect:/enfermedad/listar";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEnfermedad(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));


        enfermedadSvc.eliminarPorId(id);


        return "redirect:/persona/listar";
    }

}

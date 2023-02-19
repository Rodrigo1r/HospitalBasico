package com.hospital.Controlador;


import java.util.List;

import com.hospital.Servicio.EspecialidadServicio;
import com.hospital.Servicio.RolServicio;
import com.hospital.Util.NombresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.Modelo.Persona;
import com.hospital.Servicio.PersonaServicio;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaServicio personaServicio;
    @Autowired
    private RolServicio rolServicio;

    @Autowired
    EspecialidadServicio especialidadServicio;

    @GetMapping("/registro")
    public String nuevaPersona(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("persona", new Persona());
        return "persona/form-registro";
    }

    @PostMapping("/registro")
    public String registroPersona(Persona persona) {

        persona.setRoles(List.of(rolServicio.addRolPaciente()));
        personaServicio.insertar(persona);
        return "redirect:/";

    }

    @GetMapping("/agregar")
    public String agregarPersona(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("persona", new Persona());
        model.addAttribute("roles", rolServicio.obtenerRolesActivos());
        return "persona/form-nueva-persona";
    }

    @PostMapping("/agregar")
    public String nuevaPersona(Persona persona, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        //Primero valido que la Identificacion, usuario y correo no existan, porque deben ser unicos
        var buscapersona = personaServicio.buscarPersona(persona);
        if (buscapersona == null && persona.getIdentificacion().length() == 10) {
            //persona.setRoles(List.of(rolServicio.addRolAdministrador()));
            personaServicio.insertar(persona);
            return "redirect:/persona/listar";
        }

        if (persona.getIdentificacion().length() < 10) {
            model.addAttribute("pIdentif", "La identificacion tiene una longitud incorrecta");
        }
        model.addAttribute("roles", rolServicio.obtenerRolesActivos());
        model.addAttribute("pExiste", buscapersona);
        return "persona/form-nueva-persona";

    }

    @GetMapping("/listar")
    public String listado(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("lista", personaServicio.listarTodos());
        model.addAttribute("roles", rolServicio.obtenerRolesActivos());
        return "persona/lista-personas";
    }


    @GetMapping("/editar/{id}")
    public String editarPersona(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        var persona = personaServicio.buscaPersonaPorId(id);
        model.addAttribute("persona", persona);
        model.addAttribute("titulo", "Actualizar usuario");
        model.addAttribute("roles", rolServicio.obtenerRolesActivos());
        return "persona/form-actualizar-persona";
    }

    @PostMapping("/editar/{id}")
    public String actualizarPersona(@PathVariable long id, Persona persona, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        
        //Primero valido que la Identificacion, usuario y correo no existan, porque deben ser unicos
        if (persona.getIdentificacion().length() == 10) {
            //persona.setRoles(List.of(rolServicio.addRolAdministrador()));
            personaServicio.actualizarPersona(persona);
            return "redirect:/persona/listar";
        }

        if (persona.getIdentificacion().length() < 10) {
            model.addAttribute("pIdentif", "La identificacion tiene una longitud incorrecta");
        }
        model.addAttribute("roles", rolServicio.obtenerRolesActivos());
        model.addAttribute("persona", personaServicio.buscaPersonaPorId(id));
        return "persona/form-actualizar-persona";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));


        personaServicio.eliminarPorId(id);


        return "redirect:/persona/listar";
    }

    @GetMapping("/listaMedicos")
    public String gestionarEspecialidad(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        var listaMedicos = personaServicio.listarPersonasPorRol(NombresRoles.MEDICO);
        model.addAttribute("listaMed", listaMedicos);
        return "persona/form-lista-medicos";
    }


    @GetMapping("/gestionMedico/{id}")
    public String gestionMedico(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        var persona = personaServicio.buscaPersonaPorId(id);
        model.addAttribute("titulo", "Gestion de Medicos y Especialidades");
        model.addAttribute("persona", persona);
        model.addAttribute("listaEspe", especialidadServicio.buscaEspecialidadPorEstado(true));
        return "persona/form-gestion-medico";
    }

    @PostMapping("/gestionMedico/{id}")
    public String actualizarMedico(@PathVariable long id, Persona persona, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        var medico = personaServicio.buscaPersonaPorId(id);
        medico.setEspecialidades(persona.getEspecialidades());
        //Primero valido que la Identificacion, usuario y correo no existan, porque deben ser unicos

        //persona.setRoles(List.of(rolServicio.addRolAdministrador()));
        personaServicio.actualizarMedico(medico);
        return "redirect:/persona/listaMedicos";


    }
}

package com.hospital.Controlador;


import com.hospital.Modelo.Especialidad;
import com.hospital.Servicio.EspecialidadServicio;
import com.hospital.Servicio.PersonaServicio;
import com.hospital.Util.NombreMayusculaEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/especialidad")
public class EspecialidadController {

    @Autowired
    EspecialidadServicio especialidadServicio;

    @Autowired
    PersonaServicio personaServicio;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, "nombreEspecialidad", new NombreMayusculaEditor());

    }

    @GetMapping("/registro")
    public String nuevaEspecialidad(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("especialidad", new Especialidad());
        return "especialidad/form-nueva-especialidad";
    }

    @PostMapping("/registro")
    public String registroEspecialidad(Especialidad especialidad, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        //Primero valido que la especialidad no exista, porque debe ser uncica
        if (!(especialidadServicio.buscaEspecialidadPorNombre(especialidad.getNombreEspecialidad()))) {
            model.addAttribute("espeExiste", null);
            especialidadServicio.insertar(especialidad);
            return "redirect:/especialidad/listar";
        } else {
            model.addAttribute("espeExiste", "El nombre de la especialidad ya existe");
            return "especialidad/form-nueva-especialidad";
        }


    }

    @GetMapping("/listar")
    public String listado(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("lista", especialidadServicio.listar());
        return "especialidad/form-lista-especialidad";
    }

    @GetMapping("/editar/{id}")
    public String editarEspecialidad(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        var especialidad = especialidadServicio.buscarEspecialidadPorId(id);
        model.addAttribute("especialidad", especialidad);
        return "especialidad/form-actualiza-especialidad";
    }

    @PostMapping("/editar/{id}")
    public String actualizarPersona(@PathVariable long id, Especialidad especialidad, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        especialidadServicio.actualizar(especialidad);
        return "redirect:/especialidad/listar";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEspecialidad(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        especialidadServicio.eliminarEspecialidadPorId(id);

        return "redirect:/especialidad/listar";
    }


}

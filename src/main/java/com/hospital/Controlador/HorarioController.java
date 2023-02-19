package com.hospital.Controlador;

import com.hospital.Modelo.ConfigurarHorario;
import com.hospital.Servicio.ConfigurarHorarioServicio;
import com.hospital.Servicio.HorarioAtencionServicio;
import com.hospital.Servicio.PersonaServicio;
import com.hospital.Util.DiasSemana;
import com.hospital.Util.NombresRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    ConfigurarHorarioServicio configurarHorarioServicio;

    @Autowired
    PersonaServicio personaServicio;

    @Autowired
    HorarioAtencionServicio horarioAtencionServicio;


    @GetMapping("/configurar")
    public String agregarConfiguracion(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("configuracion", new ConfigurarHorario());
        var dias = DiasSemana.listaDias();
        model.addAttribute("listaDias", dias);
        return "horario/form-configurar-horario";
    }

    @PostMapping("/configurar")
    public String grabarConfiguracion(ConfigurarHorario configurarHorario, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        //Primero valido que la especialidad no exista, porque debe ser uncica
        configurarHorarioServicio.insertar(configurarHorario);

        return "redirect:/horario/listar";
    }


    @GetMapping("/listar")
    public String listado(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("lista", configurarHorarioServicio.listar());
        return "horario/form-lista-horario";
    }

    @GetMapping("/editar/{id}")
    public String editarHorario(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        var horario = configurarHorarioServicio.buscarConfiguracionPorId(id);
        var dias = DiasSemana.listaDias();
        model.addAttribute("listaDias", dias);
        model.addAttribute("configuracion", horario);
        return "horario/form-actualizar-horario";
    }

    @PostMapping("/editar/{id}")
    public String actualizarHorario(@PathVariable long id, ConfigurarHorario configurarHorario, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        configurarHorarioServicio.actualizar(configurarHorario);
        return "redirect:/horario/listar";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarHorario(@PathVariable long id, Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        configurarHorarioServicio.eliminarPorId(id);

        return "redirect:/horario/listar";
    }

    @GetMapping("/listaHorariosPlanificados")
    public String actualizarHorarios(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        var listaHorarios = horarioAtencionServicio.buscarMedicosConHorarios();
        model.addAttribute("listaHorarios", listaHorarios);
        return "horario/lista-planificacion-horario";
    }

    @GetMapping("/planificarHorarios")
    public String planificarHorarios(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));

        var listaMedicos = personaServicio.listarPersonasPorRol(NombresRoles.MEDICO);
        var genHoras = horarioAtencionServicio.generarHorarios();
        personaServicio.agregaHorarios(listaMedicos, genHoras);

        var listaHorarios = horarioAtencionServicio.buscarMedicosConHorarios();
        model.addAttribute("listaHorarios", listaHorarios);
        return "redirect:/horario/listaHorariosPlanificados";
    }

    @RequestMapping(value = "/desactivarHorarioPorId", method = RequestMethod.GET)
    public @ResponseBody String validaCitasHorario(@RequestParam(value = "idHorario", required = true) Long id) {

        horarioAtencionServicio.cambiarEstadoHorarioPorId(id,false);

        return "redirect:/horario/listaHorariosPlanificados";

    }


}

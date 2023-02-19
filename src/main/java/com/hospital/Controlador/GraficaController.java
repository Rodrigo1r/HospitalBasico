package com.hospital.Controlador;

import javax.servlet.http.HttpSession;

import com.hospital.Servicio.AtencionMedicaServicio;
import com.hospital.Servicio.CitaMedicaServicio;
import com.hospital.Servicio.PersonaServicio;
import com.hospital.Util.MesesAnio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.Modelo.ConfigurarHorario;
import com.hospital.Util.DiasSemana;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/grafica")
public class GraficaController {
	
	@Autowired
    CitaMedicaServicio citaMedicaSvc;

    @Autowired
    AtencionMedicaServicio atencionMedicaSvc;

    @Autowired
    PersonaServicio personaSvc;

    @GetMapping("/grafica")
    public String graficaGeneral(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("configuracion", new ConfigurarHorario());
        //var meses = MesesAnio.listaMeses().stream().map(x->x.getNombreMes()).collect(Collectors.toList());
        var citasMes = citaMedicaSvc.totalCitasPorMes().stream().map(x->x.getCantidad()).collect(Collectors.toList());
        var atencionesMes = atencionMedicaSvc.totalAtencionesPorMes().stream().map(x->x.getCantidad()).collect(Collectors.toList());
        var pacientesMes = personaSvc.totalPacientesPorMes().stream().map(x->x.getCantidad()).collect(Collectors.toList());
        var pacientesMesActual = personaSvc.totalPacientesMesActual();
        var pacientesNombreMes = personaSvc.totalPacientesPorMes().stream().map(x->x.getMes()).collect(Collectors.toList());
        var pacientesCantidadMes = personaSvc.totalPacientesPorMes().stream().map(x->x.getCantidad()).collect(Collectors.toList());

        var nombreEspecialidades = citaMedicaSvc.listadoCitasAgendadasPorEspecialidad().stream().map(x->x.getNombre_especialidad()).collect(Collectors.toList());
        var cantidadEspecialidades = citaMedicaSvc.listadoCitasAgendadasPorEspecialidad().stream().map(x->x.getCantidad()).collect(Collectors.toList());
        //En esta parte del codigo busco los datos de las citas medicas
        var citasHoy = citaMedicaSvc.totalCitasHoy();
        var citasAyer = citaMedicaSvc.totalCitasHoy();
        Integer porcentaje = 0;
        if(citasAyer == 0 ){
            citasAyer = 1;
        }
            porcentaje = ((citasHoy-citasAyer)*100)/citasAyer;


        model.addAttribute("citaMes", citasMes);
        model.addAttribute("atencionMes", atencionesMes);
        model.addAttribute("pacienteMes", pacientesMes);
        model.addAttribute("pacientesNombreMes", pacientesNombreMes);
        model.addAttribute("pacientesCantidadMes", pacientesCantidadMes);
        model.addAttribute("nombreEspecialidades",nombreEspecialidades );
        model.addAttribute("cantidadEspecialidades",cantidadEspecialidades );
        model.addAttribute("pacientesMesActual", pacientesMesActual);
        model.addAttribute("citasHoy", citasHoy);
        model.addAttribute("porcentaje", porcentaje);

        var citasMesActual = citaMedicaSvc.totalCitasMesActual();
        var citasMesAnterior = citaMedicaSvc.totalCitasMesAnterior();
        if(citasMesAnterior == 0 ){
            citasMesAnterior = 1;
        }
        Integer porcentajeMes = 0;
            porcentajeMes = ((citasMesActual-citasMesAnterior)*100)/citasMesAnterior;

        model.addAttribute("citasMesActual", citasMesActual);
        model.addAttribute("porcentajeMes", porcentajeMes);


        var citasAnioActual = citaMedicaSvc.totalCitasAnioAactual();
        var citasAnioAnterior = citaMedicaSvc.totalCitasAnioAnterior();

        Integer porcentajeAnio = 0;

        if(citasAnioAnterior == 0 ){
            citasAnioAnterior = 1;
        }

            porcentajeAnio = ((citasAnioActual-citasAnioAnterior)*100)/citasAnioAnterior;

        model.addAttribute("citasAnioActual", citasAnioActual);
        model.addAttribute("porcentajeAnio", porcentajeAnio);

        //En esta parte del codigo busco los datos de las atenciones medicas
        var atencionHoy = atencionMedicaSvc.atencionesHoy();
        var atencionAyer = atencionMedicaSvc.atencionesAyer();
        Integer porcAtencion = 0;
        if(atencionAyer == 0 ){
            atencionAyer = 1;
        }
        porcAtencion = ((atencionHoy-atencionAyer)*100)/atencionAyer;

        model.addAttribute("atencionHoy", atencionHoy);
        model.addAttribute("porcAtencion", porcAtencion);
        
        //Datos de atenciones del mes
        var atencionMesActual = atencionMedicaSvc.atencionesMesActual();
        var atencionMesAnterior = atencionMedicaSvc.atencionesMesAnterior();
        Integer porcAtencionMes = 0;
        if(atencionMesAnterior == 0 ){
        	atencionMesAnterior = 1;
        }
        porcAtencion = ((atencionMesActual-atencionMesAnterior)*100)/atencionMesAnterior;

        model.addAttribute("atencionMesActual", atencionMesActual);
        model.addAttribute("porcAtencionMes", porcAtencionMes);
        
        //Datos de atenciones del año
        var atencionAnioActual = atencionMedicaSvc.atencionesAnioActual();
        var atencionAnioAnterior = atencionMedicaSvc.atencionesAnioAnterior();
        Integer porcAtencionAnio = 0;
        if(atencionAnioAnterior == 0 ){
        	atencionAnioAnterior = 1;
        }
        porcAtencion = ((atencionAnioActual-atencionAnioAnterior)*100)/atencionAnioAnterior;

        model.addAttribute("atencionAnioActual", atencionAnioActual);
        model.addAttribute("porcAtencionAnio", porcAtencionAnio);

        return "grafica/grafica_general";
    }

    @GetMapping("/cita")
    public String graficaCitas(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("configuracion", new ConfigurarHorario());
        var dias = DiasSemana.listaDias();
        model.addAttribute("listaDias", dias);
        
        return "grafica/graficas_citas";
    }

    @GetMapping("/atencion")
    public String graficaAtenciones(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("configuracion", new ConfigurarHorario());
        var dias = DiasSemana.listaDias();
        model.addAttribute("listaDias", dias);
        return "grafica/graficas_atenciones";
    }

}

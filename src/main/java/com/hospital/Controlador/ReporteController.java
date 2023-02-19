package com.hospital.Controlador;


import com.hospital.Genericos.ICitasMedicas;
import com.hospital.Servicio.CitaMedicaServicio;
import com.hospital.Util.Exportar.CitaAtendidasExportarPDF;
import com.hospital.Util.Exportar.CitasAtendidasExportarExcel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    CitaMedicaServicio citaMedicaSvc;

    @GetMapping("/todasCitasAtendidas")
    public String listadoAtendidas(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("lista", citaMedicaSvc.listadoCitasPorAtencion(true));
        model.addAttribute("titulo","Listado de citas atendidas");
        return "reportes/form-reporte-atendidos";
    }




    @GetMapping("/citasAtendidasEspecialidad")
    public String listadoCitasAtendidasEspecialidad(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("lista", citaMedicaSvc.listadoCitasAtendidasPorEspecialidad());
        model.addAttribute("titulo","Listado de citas atendidas por Especialidad");
        return "reportes/form-reporte-atendidos-especialidad";
    }

    @GetMapping("/citasAtendidasMedico")
    public String listadocitasAtendidasMedico(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("lista", citaMedicaSvc.listadoCitasAtendidasPorMedico());
        model.addAttribute("titulo","Listado de citas atendidas por Medico");
        return "reportes/form-reporte-atendidos-medico";
    }

    @GetMapping("/citasAtendidasMes")
    public String listadocitasAtendidasMes(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("lista", citaMedicaSvc.listadoCitasAtendidasPorMes());
        model.addAttribute("titulo","Listado de citas atendidas por Mes");
        return "reportes/form-reporte-atendidos-mes";
    }


    //Estos controladores son para el Listado de citas Atendidas
    @GetMapping("/exportarPDF")
    public void exportarTodoPDF(HttpServletResponse response) throws IOException , URISyntaxException {
        response.setContentType("application/pdf");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAtendidas_" + fechaActual + ".pdf";

        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> citas = citaMedicaSvc.listadoCitasPorAtencion(true);

        CitaAtendidasExportarPDF exporter = new CitaAtendidasExportarPDF(citas,1);
        exporter.exportar(response);


    }
    
    
    @GetMapping("/exportarEspecialidadPDF")
    public void exportarEspecialidadPDF(HttpServletResponse response) throws IOException , URISyntaxException{
        response.setContentType("application/pdf");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAtendidasEspecialidad_" + fechaActual + ".pdf";

        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> citas = citaMedicaSvc.listadoCitasAtendidasPorEspecialidad();

        CitaAtendidasExportarPDF exporter = new CitaAtendidasExportarPDF(citas,2);
        exporter.exportar(response);


    }
    
    @GetMapping("/exportarMedicoPDF")
    public void exportarMedicoPDF(HttpServletResponse response) throws IOException , URISyntaxException{
        response.setContentType("application/pdf");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAtendidasMedico_" + fechaActual + ".pdf";

        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> citas = citaMedicaSvc.listadoCitasAtendidasPorMedico();

        CitaAtendidasExportarPDF exporter = new CitaAtendidasExportarPDF(citas,3);
        exporter.exportar(response);


    }
    @GetMapping("/exportarMesPDF")
    public void exportarMesPDF(HttpServletResponse response) throws IOException , URISyntaxException{
        response.setContentType("application/pdf");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAtendidasMes_" + fechaActual + ".pdf";

        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> citas = citaMedicaSvc.listadoCitasAtendidasPorMes();

        CitaAtendidasExportarPDF exporter = new CitaAtendidasExportarPDF(citas,4);
        exporter.exportar(response);


    }

    @GetMapping("/exportarExcel")
    public void exportarTodoExcel(Model model,HttpServletResponse response) throws IOException {
        response.setContentType("application/octec-stream");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAtendidas_" + fechaActual + ".xlsx";


        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> todasCitas = citaMedicaSvc.listadoCitasPorAtencion(true);

        CitasAtendidasExportarExcel export = new CitasAtendidasExportarExcel(todasCitas,1);
        export.exportar(response);
    }
    @GetMapping("/exportarEspecialidadExcel")
    public void exportarEspecialidadExcel(Model model,HttpServletResponse response) throws IOException {
        response.setContentType("application/octec-stream");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAtendidasEspecialidad_" + fechaActual + ".xlsx";


        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> todasCitas = citaMedicaSvc.listadoCitasAtendidasPorEspecialidad();

        CitasAtendidasExportarExcel export = new CitasAtendidasExportarExcel(todasCitas,2);
        export.exportar(response);
    }
    
    @GetMapping("/exportarMedicoExcel")
    public void exportarMedicoExcel(Model model,HttpServletResponse response) throws IOException {
        response.setContentType("application/octec-stream");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAtendidasMedico_" + fechaActual + ".xlsx";


        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> todasCitas = citaMedicaSvc.listadoCitasAtendidasPorMedico();

        CitasAtendidasExportarExcel export = new CitasAtendidasExportarExcel(todasCitas,3);
        export.exportar(response);
    }

    @GetMapping("/exportarMesExcel")
    public void exportarMesExcel(Model model,HttpServletResponse response) throws IOException {
        response.setContentType("application/octec-stream");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAtendidasMes_" + fechaActual + ".xlsx";


        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> todasCitas = citaMedicaSvc.listadoCitasAtendidasPorMes();

        CitasAtendidasExportarExcel export = new CitasAtendidasExportarExcel(todasCitas,4);
        export.exportar(response);
    }


    //Estos controladores son para el Listado de citas AGENDADAS
    @GetMapping("/todasCitasAgendadas")
    public String listadoAgendadas(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("lista", citaMedicaSvc.listadoCitasAgendadas());
        model.addAttribute("titulo","Listado de citas agendadas");
        return "reportes/form-reporte-agendados";
    }

    @GetMapping("/citasAgendadasEspecialidad")
    public String listadoCitasAgendadasEspecialidad(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("lista", citaMedicaSvc.listadoCitasAgendadasPorEspecialidad());
        model.addAttribute("titulo","Listado de citas agendadas por Especialidad");
        return "reportes/form-reporte-agendados-especialidad";
    }

    @GetMapping("/citasAgendadasMedico")
    public String listadocitasAgendadasMedico(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("lista", citaMedicaSvc.listadoCitasAgendadasPorMedico());
        model.addAttribute("titulo","Listado de citas agendadas por Medico");
        return "reportes/form-reporte-agendados-medico";
    }

    @GetMapping("/citasAgendadasMes")
    public String listadocitasAgendadasMes(Model model, HttpSession session) {
        model.addAttribute("dpersona", session.getAttribute("datoUser"));
        model.addAttribute("lista", citaMedicaSvc.listadoCitasAgendadasPorMes());
        model.addAttribute("titulo","Listado de citas agendadas por Mes");
        return "reportes/form-reporte-agendados-mes";
    }



    @GetMapping("/exportarAgendaPDF")
    public void exportarAgendaPDF(HttpServletResponse response) throws IOException , URISyntaxException {
        response.setContentType("application/pdf");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAgendadas_" + fechaActual + ".pdf";

        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> citas = citaMedicaSvc.listadoCitasAgendadas();

        CitaAtendidasExportarPDF exporter = new CitaAtendidasExportarPDF(citas,1);
        exporter.exportar(response);


    }


    @GetMapping("/exportarAgendaEspecialidadPDF")
    public void exportarAgendaEspecialidadPDF(HttpServletResponse response) throws IOException , URISyntaxException{
        response.setContentType("application/pdf");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAgendadasEspecialidad_" + fechaActual + ".pdf";

        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> citas = citaMedicaSvc.listadoCitasAgendadasPorEspecialidad();

        CitaAtendidasExportarPDF exporter = new CitaAtendidasExportarPDF(citas,2);
        exporter.exportar(response);


    }

    @GetMapping("/exportarAgendaMedicoPDF")
    public void exportarAgendaMedicoPDF(HttpServletResponse response) throws IOException , URISyntaxException {
        response.setContentType("application/pdf");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAgendadasMedico_" + fechaActual + ".pdf";

        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> citas = citaMedicaSvc.listadoCitasAgendadasPorMedico();

        CitaAtendidasExportarPDF exporter = new CitaAtendidasExportarPDF(citas,3);
        exporter.exportar(response);


    }
    @GetMapping("/exportarAgendaMesPDF")
    public void exportarAgendaMesPDF(HttpServletResponse response) throws IOException , URISyntaxException {
        response.setContentType("application/pdf");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAgendadasMes_" + fechaActual + ".pdf";

        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> citas = citaMedicaSvc.listadoCitasAgendadasPorMes();

        CitaAtendidasExportarPDF exporter = new CitaAtendidasExportarPDF(citas,4);
        exporter.exportar(response);


    }

    @GetMapping("/exportarAgendaExcel")
    public void exportarAgendaTodoExcel(Model model,HttpServletResponse response) throws IOException {
        response.setContentType("application/octec-stream");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAgendadas_" + fechaActual + ".xlsx";


        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> todasCitas = citaMedicaSvc.listadoCitasAgendadas();

        CitasAtendidasExportarExcel export = new CitasAtendidasExportarExcel(todasCitas,1);
        export.exportar(response);
    }
    @GetMapping("/exportarAgendaEspecialidadExcel")
    public void exportarAgendaEspecialidadExcel(Model model,HttpServletResponse response) throws IOException {
        response.setContentType("application/octec-stream");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAgendadasEspecialidad_" + fechaActual + ".xlsx";


        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> todasCitas = citaMedicaSvc.listadoCitasAgendadasPorEspecialidad();

        CitasAtendidasExportarExcel export = new CitasAtendidasExportarExcel(todasCitas,2);
        export.exportar(response);
    }

    @GetMapping("/exportarAgendaMedicoExcel")
    public void exportarAgendaMedicoExcel(Model model,HttpServletResponse response) throws IOException {
        response.setContentType("application/octec-stream");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAgendadasMedico_" + fechaActual + ".xlsx";


        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> todasCitas = citaMedicaSvc.listadoCitasAgendadasPorMedico();

        CitasAtendidasExportarExcel export = new CitasAtendidasExportarExcel(todasCitas,3);
        export.exportar(response);
    }

    @GetMapping("/exportarAgendaMesExcel")
    public void exportarAgendaMesExcel(Model model,HttpServletResponse response) throws IOException {
        response.setContentType("application/octec-stream");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatter.format(new Date());

        String cabecera = "Content-Disposition";
        String nombreArchivo = "attachment; filename=CitasAgendadasMes_" + fechaActual + ".xlsx";


        response.setHeader(cabecera, nombreArchivo);

        List<ICitasMedicas> todasCitas = citaMedicaSvc.listadoCitasAgendadasPorMes();

        CitasAtendidasExportarExcel export = new CitasAtendidasExportarExcel(todasCitas,4);
        export.exportar(response);
    }
}

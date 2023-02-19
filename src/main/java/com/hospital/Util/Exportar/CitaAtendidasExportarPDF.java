package com.hospital.Util.Exportar;

import com.hospital.Genericos.ICitasMedicas;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.lowagie.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;


import java.awt.Color;


import java.net.URISyntaxException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CitaAtendidasExportarPDF {

	private List<ICitasMedicas> listaAtendidas;
	private Integer opcion;

	public CitaAtendidasExportarPDF(List<ICitasMedicas> listaAtendidas, Integer opcion) {
		this.listaAtendidas = listaAtendidas;
		this.opcion = opcion;
	}

	private void escribirCabeceraPdf(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();



		celda.setBackgroundColor(Color.BLUE);
		celda.setPadding(5);
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);

		switch (opcion) {

		case 1:
			// Agregar Todos los campos del Pdf
			celda.setPhrase(new Phrase("Fecha", fuente));
			tabla.addCell(celda);
			celda.setPhrase(new Phrase("Nombres", fuente));
			tabla.addCell(celda);
			celda.setPhrase(new Phrase("Apellidos", fuente));
			tabla.addCell(celda);
			celda.setPhrase(new Phrase("Médico", fuente));
			tabla.addCell(celda);
			celda.setPhrase(new Phrase("Especialidad", fuente));
			tabla.addCell(celda);
			celda.setPhrase(new Phrase("Horario", fuente));
			break;

		case 2:
			// Agregar Solo campo Especialidad

			celda.setPhrase(new Phrase("Especialidad", fuente));
			tabla.addCell(celda);
			celda.setPhrase(new Phrase("Cantidad", fuente));
			break;


		case 3:
			// Agregar Solo Campo Medico

			celda.setPhrase(new Phrase("Médico", fuente));
			tabla.addCell(celda);
			celda.setPhrase(new Phrase("Cantidad", fuente));
			break;

		case 4:
			// Agregar Solo Campo Mes
			celda.setPhrase(new Phrase("Mes", fuente));
			tabla.addCell(celda);
			celda.setPhrase(new Phrase("Cantidad", fuente));
			break;

		}
		tabla.addCell(celda);
	}

	private void escribirDatosDeLaTabla(PdfPTable tabla) {

		switch (opcion) {
		case 1:
			//Agrego todos los elementos 
			for (ICitasMedicas cita : listaAtendidas) {
				tabla.addCell(cita.getFecha_dia().toString());
				tabla.addCell(cita.getNombres());
				tabla.addCell(cita.getApellidos());
				tabla.addCell(cita.getNombre_medico());
				tabla.addCell(cita.getNombre_especialidad());
				tabla.addCell(cita.getHorario().toString());
			}
			break;
			
		case 2:
			//Agrego Solo Especialidad
			for (ICitasMedicas cita : listaAtendidas) {
				tabla.addCell(cita.getNombre_especialidad());
				tabla.addCell(cita.getCantidad().toString());
			}
			break;
		case 3:
			//Agrego Solo Medico
			for (ICitasMedicas cita : listaAtendidas) {
				tabla.addCell(cita.getNombre_medico());
				tabla.addCell(cita.getCantidad().toString());
			}
			break;
			
		case 4:
			//Agrego Solo Mes
			for (ICitasMedicas cita : listaAtendidas) {
				tabla.addCell(cita.getMes());
				tabla.addCell(cita.getCantidad().toString());
			}
			break;
		}

	}

	public void exportar(HttpServletResponse response) throws DocumentException, IOException, URISyntaxException {
		Document documento = new Document(PageSize.A4);

		PdfWriter.getInstance(documento, response.getOutputStream());

		documento.open();
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.BLUE);
		fuente.setSize(18);

		//Fuente para mostrar la fecha actual
		Font fuenteFecha = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuenteFecha.setSize(8);
		//Obtener la fecha actual
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String fechaActual = formatter.format(new Date());

		//Ruta general del Classpath + la ruta estatica de mi imagen
		String logo = new ClassPathResource("static/assets/img/hospital_180.png").getFile().getPath();
		Image image = Image.getInstance(logo);
		image.scaleAbsolute(175f , 50f);
		image.setAlignment(Paragraph.ALIGN_CENTER);

		Paragraph fecha = new Paragraph("Generado el : " + fechaActual, fuenteFecha);
		fecha.setAlignment(Paragraph.ALIGN_RIGHT);
		documento.add(fecha);


		documento.add(image);


		Paragraph titulo = null;
		switch (opcion){
			case 1:
				titulo = new Paragraph("Lista de pacientes Atendidos", fuente);
				break;
				
			case 2:
				titulo = new Paragraph("Lista de pacientes Atendidos - Por Especialidad", fuente);
				break;
				
			case 3:
				titulo = new Paragraph("Lista de pacientes Atendidos - Por Medico", fuente);
				break;
				
			case 4:
				titulo = new Paragraph("Lista de pacientes Atendidos - Por Mes", fuente);
				break;

		}
		
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);

		PdfPTable tabla = null;
		
		switch (opcion) {
		case 1:
			tabla = new PdfPTable(6);
			tabla.setWidths(new float[] { 1.5f, 1.5f, 1.5f, 2.5f, 2.5f, 1.2f });
			break;
		case 2 :
			tabla = new PdfPTable(2);
			tabla.setWidths(new float[] { 2.5f, 1.2f });
			break;
		case 3 :
			tabla = new PdfPTable(2);
			tabla.setWidths(new float[] {  2.5f, 1.2f });
			break;
		case 4 :
			tabla = new PdfPTable(2);
			tabla.setWidths(new float[] { 2.5f, 1.2f });
			break;
		
		}

		tabla.setWidthPercentage(110);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);

		escribirCabeceraPdf(tabla);
		escribirDatosDeLaTabla(tabla);

		documento.add(tabla);
		documento.close();
	}
}

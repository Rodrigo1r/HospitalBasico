package com.hospital.Util.Exportar;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.hospital.Genericos.ICitasMedicas;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class CitasAtendidasExportarExcel {

    private XSSFWorkbook libro;
    private XSSFSheet hoja;

    private List<ICitasMedicas> listaAtendidas;
    private Integer opcion;

    public CitasAtendidasExportarExcel(List<ICitasMedicas> liAtendidas , Integer opcion) {
        this.listaAtendidas = liAtendidas;
        this.opcion = opcion;
        libro = new XSSFWorkbook();
        hoja = libro.createSheet("CitasAtendidas");
    }

    private void escribirCabeceraExcel() {
        Row fila = hoja.createRow(0);

        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setBold(true);
        fuente.setFontHeight(16);
        estilo.setFont(fuente);
        
        Cell celda = fila.createCell(0);
        switch(opcion){
        case 1:
        	
            celda.setCellValue("Fecha");
            celda.setCellStyle(estilo);

            celda = fila.createCell(1);
            celda.setCellValue("Nombres");
            celda.setCellStyle(estilo);

            celda = fila.createCell(2);
            celda.setCellValue("Apellidos");
            celda.setCellStyle(estilo);

            celda = fila.createCell(3);
            celda.setCellValue("Medico");
            celda.setCellStyle(estilo);

            celda = fila.createCell(4);
            celda.setCellValue("Especialidad");
            celda.setCellStyle(estilo);

            celda = fila.createCell(5);
            celda.setCellValue("Horario");
            celda.setCellStyle(estilo);
            break;
        case 2:
           //Especialidad
           
            celda.setCellValue("Especialidad");
            celda.setCellStyle(estilo);

            celda = fila.createCell(1);
            celda.setCellValue("Cantidad");
            celda.setCellStyle(estilo);
            break;
        case 3:
            //Medico
        
            celda.setCellValue("Medico");
            celda.setCellStyle(estilo);

            celda = fila.createCell(1);
            celda.setCellValue("Cantidad");
            celda.setCellStyle(estilo);
            break;
        case 4:
            
            celda.setCellValue("Especialidad");
            celda.setCellStyle(estilo);

            celda = fila.createCell(1);
            celda.setCellValue("Cantidad");
            celda.setCellStyle(estilo);
            break;
        
        }

    }

    private void escribirDatosDeLaTabla() {
        int numeroFilas = 1;

        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);
        
        switch(opcion){
        	case 1:
        		  for(ICitasMedicas cita : listaAtendidas) {
        	            Row fila = hoja.createRow(numeroFilas ++);

        	            Cell celda = fila.createCell(0);
        	            celda.setCellValue(cita.getFecha_dia().toString());
        	            hoja.autoSizeColumn(0);
        	            celda.setCellStyle(estilo);

        	            celda = fila.createCell(1);
        	            celda.setCellValue(cita.getNombres());
        	            hoja.autoSizeColumn(1);
        	            celda.setCellStyle(estilo);

        	            celda = fila.createCell(2);
        	            celda.setCellValue(cita.getApellidos());
        	            hoja.autoSizeColumn(2);
        	            celda.setCellStyle(estilo);

        	            celda = fila.createCell(3);
        	            celda.setCellValue(cita.getNombre_medico());
        	            hoja.autoSizeColumn(3);
        	            celda.setCellStyle(estilo);

        	            celda = fila.createCell(4);
        	            celda.setCellValue(cita.getNombre_especialidad());
        	            hoja.autoSizeColumn(4);
        	            celda.setCellStyle(estilo);

        	            celda = fila.createCell(5);
        	            celda.setCellValue(cita.getHorario().toString());
        	            hoja.autoSizeColumn(5);
        	            celda.setCellStyle(estilo);
        		
        }
                  break;
            case 2:
                //Especialidad
                for(ICitasMedicas cita : listaAtendidas) {
                    Row fila = hoja.createRow(numeroFilas ++);

                    Cell celda = fila.createCell(0);
                    celda.setCellValue(cita.getNombre_especialidad());
                    hoja.autoSizeColumn(0);
                    celda.setCellStyle(estilo);

                    celda = fila.createCell(1);
                    celda.setCellValue(cita.getCantidad());
                    hoja.autoSizeColumn(1);
                    celda.setCellStyle(estilo);
                }
                break;

                case 3 :
                    //Medico
                    for(ICitasMedicas cita : listaAtendidas) {
                        Row fila = hoja.createRow(numeroFilas ++);

                        Cell celda = fila.createCell(0);
                        celda.setCellValue(cita.getNombre_medico());
                        hoja.autoSizeColumn(0);
                        celda.setCellStyle(estilo);

                        celda = fila.createCell(1);
                        celda.setCellValue(cita.getCantidad());
                        hoja.autoSizeColumn(1);
                        celda.setCellStyle(estilo);
                    }
                    break;

            case 4:
                //Mes
                for(ICitasMedicas cita : listaAtendidas) {
                    Row fila = hoja.createRow(numeroFilas ++);

                    Cell celda = fila.createCell(0);
                    celda.setCellValue(cita.getMes());
                    hoja.autoSizeColumn(0);
                    celda.setCellStyle(estilo);

                    celda = fila.createCell(1);
                    celda.setCellValue(cita.getCantidad());
                    hoja.autoSizeColumn(1);
                    celda.setCellStyle(estilo);
                }
                break;

        }
    }

    public void exportar(HttpServletResponse response) throws IOException {
        escribirCabeceraExcel();
        escribirDatosDeLaTabla();

        ServletOutputStream outPutStream = response.getOutputStream();
        libro.write(outPutStream);

        libro.close();
        outPutStream.close();
    }



}

package com.hospital.Genericos;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CitasMedicas {

    private Long id_horario;
    private String nombres;

    private String apellidos;

    private Long id_cita;

    private Long id_persona;

    private String nombre_especialidad;

    private LocalTime horario;

    private Long id_medico;

    private String nombre_medico;

    private Integer cantidad;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_dia;

    private Boolean estado;

    private String mes;

    private String motivo;
    private String sintomas;
    private String tratamiento;


   
}

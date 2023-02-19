package com.hospital.Genericos;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;


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

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Long getId_horario() {
        return id_horario;
    }

    public void setId_horario(Long id_horario) {
        this.id_horario = id_horario;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Long getId_cita() {
        return id_cita;
    }

    public Long getId_persona() {
        return id_persona;
    }

    public String getNombre_especialidad() {
        return nombre_especialidad;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public Long getId_medico() {
        return id_medico;
    }

    public String getNombre_medico() {
        return nombre_medico;
    }

    public LocalDate getFecha_dia() {
        return fecha_dia;
    }

    public Boolean getEstado() {
        return estado;
    }


    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setId_cita(Long id_cita) {
        this.id_cita = id_cita;
    }

    public void setId_persona(Long id_persona) {
        this.id_persona = id_persona;
    }

    public void setNombre_especialidad(String nombre_especialidad) {
        this.nombre_especialidad = nombre_especialidad;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public void setId_medico(Long id_medico) {
        this.id_medico = id_medico;
    }

    public void setNombre_medico(String nombre_medico) {
        this.nombre_medico = nombre_medico;
    }

    public void setFecha_dia(LocalDate fecha_dia) {
        this.fecha_dia = fecha_dia;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}

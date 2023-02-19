package com.hospital.Modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "horarioAtencion")
public class HorarioAtencion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime horario;

    private Boolean activo;

    private Boolean asignado;

    @Column(name = "fecha_dia")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_dia;

    public HorarioAtencion() {
    }

    public HorarioAtencion(LocalTime horario) {
        this.horario = horario;
        this.activo = true;
        this.asignado = false;
    }

    public HorarioAtencion(LocalTime horario, Boolean estado, String asignado) {
        this.horario = horario;
        this.activo = true;
        this.asignado = false;
    }

    public HorarioAtencion(LocalDate fecha_dia, LocalTime horario, Boolean activo, Boolean asignado) {
        this.horario = horario;
        this.activo = activo;
        this.asignado = asignado;
        this.fecha_dia = fecha_dia;
    }


    public LocalDate getFecha_dia() {
        return fecha_dia;
    }

    public void setFecha_dia(LocalDate fecha_dia) {
        this.fecha_dia = fecha_dia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getAsignado() {
        return asignado;
    }

    public void setAsignado(Boolean asignado) {
        this.asignado = asignado;
    }

    @Override
    public String toString() {
        return (fecha_dia + " " + horario + " " + asignado + "\n");
    }
}

package com.hospital.Modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreEspecialidad", unique = true)
    private String nombreEspecialidad;

    @Column(name = "descripcionEspecialidad")
    private String descripcionEspecialidad;


    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecha_registro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_registro;

    @Column(name = "fecha_modificacion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_modificacion;

    @Column(name = "usuarioCreacion")
    private String usuarioCreacion;

    @Column(name = "usuarioModificacion")
    private String usuarioModificacion;

    public Especialidad() {
        this.estado = true;
    }

    public Especialidad(String nombre, String descripcionEspecialidad, Boolean estado, Date fecha_registro, String usuarioCreacion) {
        this.nombreEspecialidad = nombre;
        this.descripcionEspecialidad = descripcionEspecialidad;
        this.estado = true;
        this.fecha_registro = fecha_registro;
        this.usuarioCreacion = usuarioCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public String getDescripcionEspecialidad() {
        return descripcionEspecialidad;
    }

    public void setDescripcionEspecialidad(String descripcionEspecialidad) {
        this.descripcionEspecialidad = descripcionEspecialidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    @Override
    public String toString() {
        return nombreEspecialidad;
    }
}

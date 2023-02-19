package com.hospital.Modelo;

import com.hospital.Util.NombresRoles;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "rol")
public class Rol implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NombresRoles nombre;

    @Column(name = "estado")
    private Boolean estado;


    public Rol() {

    }

    public Rol(NombresRoles nombresRoles) {
        this.nombre = nombresRoles;
    }

    public Rol(NombresRoles nombresRoles, Boolean estado) {
        this.nombre = nombresRoles;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NombresRoles getNombre() {
        return nombre;
    }

    public void setNombre(NombresRoles nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre.toString();
    }
}

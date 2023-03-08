package com.hospital.Modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "especialidad")
public class Especialidad extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreEspecialidad", unique = true)
    private String nombreEspecialidad;

    @Column(name = "descripcionEspecialidad")
    private String descripcionEspecialidad;


    @Column(name = "estado")
    private Boolean estado;





    public Especialidad() {
        this.estado = true;
    }

    @Override
    public String toString() {
        return nombreEspecialidad;
    }
}

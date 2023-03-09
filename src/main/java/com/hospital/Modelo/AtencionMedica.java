package com.hospital.Modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "atencion_medica")
public class AtencionMedica extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String motivoConsulta;

    private String sintomas;

    private String tratamiento;

    @OneToOne
    @JoinColumn(name = "id_cita")
    private CitaMedica citaMedica;

    @OneToOne
    @JoinColumn(name = "id_receta")
    private Receta receta;

    @OneToMany(mappedBy = "atencion")
    private List<Examen> examen;



}

package com.hospital.Modelo;

import javax.persistence.*;

@Entity
@Table(name = "historia_clinica")
public class HistoriaClinica extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}

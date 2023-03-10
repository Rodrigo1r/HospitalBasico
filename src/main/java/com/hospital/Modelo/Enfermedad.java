package com.hospital.Modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enfermedad")
public class Enfermedad extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDiagnostico;

    private String nombreEnfermedad;

    private String posibleObervacion;

    private String Observaciones;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;



}

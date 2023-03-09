package com.hospital.Modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "examen")
public class Examen extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_atencion")
    private AtencionMedica atencion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private LocalTime hora;

    private String motivo;

    private String detalle;
    private String comentario;









}

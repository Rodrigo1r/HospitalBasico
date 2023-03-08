package com.hospital.Modelo;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persona")
public class Persona extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificacion", unique = true, length = 10)
    private String identificacion;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "correo", unique = true)
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "genero_id", referencedColumnName = "id")
    @Column(name = "genero")
    private String genero;

    @Column(name = "lugar_residencia")
    private String lugar_residencia;

    @Column(name = "fecha_nacimiento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_nacimiento;

    @Column(name = "usuario", unique = true, length = 30)
    private String usuario;

    @Column(name = "password", length = 128)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Rol> roles;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Especialidad> especialidades;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<HorarioAtencion> horarios;

    @Column(name = "estado" , nullable = false)
    private Boolean estado;

     //mappedBy = "persona" , cascade = CascadeType.ALL)
     @OneToOne
     @JoinColumn(name = "id_historia")
     private HistoriaClinica historia;


}

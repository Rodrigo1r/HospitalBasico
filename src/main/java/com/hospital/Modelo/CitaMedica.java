package com.hospital.Modelo;

import javax.persistence.*;

@Entity
@Table(name = "cita_medica")
public class CitaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_persona;

    private Long id_medico;

    private String nombreMedico;

    private Long id_horario;

    private Long id_especialidad;

    private Boolean estado;

    private Boolean atencion;

    @OneToOne(mappedBy = "citaMedica" , cascade = CascadeType.ALL)
    private AtencionMedica atencionMedica;

    public CitaMedica() {
    }

    public CitaMedica(Long id_persona, Long id_medico, String nombreMedico, Long id_horario, Long id_especialidad) {
        this.id_persona = id_persona;
        this.id_medico = id_medico;
        this.nombreMedico = nombreMedico;
        this.id_horario = id_horario;
        this.id_especialidad = id_especialidad;
        this.estado = true;
        this.atencion = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_persona() {
        return id_persona;
    }

    public void setId_persona(Long id_persona) {
        this.id_persona = id_persona;
    }

    public Long getId_medico() {
        return id_medico;
    }

    public void setId_medico(Long id_medico) {
        this.id_medico = id_medico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public Long getId_horario() {
        return id_horario;
    }

    public void setId_horario(Long id_horario) {
        this.id_horario = id_horario;
    }

    public Long getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(Long id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getAtencion() {
        return atencion;
    }

    public void setAtencion(Boolean atencion) {
        this.atencion = atencion;
    }

    public AtencionMedica getAtencionMedica() {
        return atencionMedica;
    }

    public void setAtencionMedica(AtencionMedica atencionMedica) {
        this.atencionMedica = atencionMedica;
    }
}

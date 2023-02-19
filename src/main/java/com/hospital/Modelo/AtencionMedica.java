package com.hospital.Modelo;

import javax.persistence.*;


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

    public AtencionMedica() {
    }


    
    public AtencionMedica(String motivoConsulta, String sintomas, String tratamiento, CitaMedica citaMedica) {
        this.motivoConsulta = motivoConsulta;
        this.sintomas = sintomas;
        this.tratamiento = tratamiento;
        this.citaMedica = citaMedica;

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public CitaMedica getCitaMedica() {
        return citaMedica;
    }

    public void setCitaMedica(CitaMedica citaMedica) {
        this.citaMedica = citaMedica;
    }

}

package com.hospital.Modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_clinico")
public class DetalleClinico {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_historia")
    private HistoriaClinica historia;
    
    @OneToOne
    @JoinColumn(name = "id_atencion")
    private AtencionMedica atencionMedica;

	public DetalleClinico(HistoriaClinica historia, AtencionMedica atencionMedica) {
		this.historia = historia;
		this.atencionMedica = atencionMedica;
	}

	public HistoriaClinica getHistoria() {
		return historia;
	}


	public void setHistoria(HistoriaClinica historia) {
		this.historia = historia;
	}

	public AtencionMedica getAtencionMedica() {
		return atencionMedica;
	}

	public void setAtencionMedica(AtencionMedica atencionMedica) {
		this.atencionMedica = atencionMedica;
	}

	public DetalleClinico() {
	}
}

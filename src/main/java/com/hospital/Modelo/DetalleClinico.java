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

    private Long idAtencion;


	public HistoriaClinica getHistoria() {
		return historia;
	}

	public void setHistoria(HistoriaClinica historia) {
		this.historia = historia;
	}



	public Long getIdAtencion() {
		return idAtencion;
	}

	public void setIdAtencion(Long idAtencion) {
		this.idAtencion = idAtencion;
	}

	public DetalleClinico(HistoriaClinica historia, Long idAtencion) {
		this.historia = historia;
		this.idAtencion = idAtencion;
	}


	public DetalleClinico( Long idAtencion) {
		this.idAtencion = idAtencion;
	}

	public DetalleClinico() {
	}
}

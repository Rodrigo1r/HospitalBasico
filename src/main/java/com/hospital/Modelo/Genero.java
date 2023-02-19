package com.hospital.Modelo;

import com.hospital.Util.NombresGenero;

import java.io.Serializable;

import javax.persistence.*;


public class Genero implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private NombresGenero nombreGenero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NombresGenero getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(NombresGenero nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	public Genero(Long id, NombresGenero nombreGenero) {
		this.id = id;
		this.nombreGenero = nombreGenero;
	}

	public Genero() {
	}

	public Genero(Long id) {
		this.id = id;
	}
	
	

}

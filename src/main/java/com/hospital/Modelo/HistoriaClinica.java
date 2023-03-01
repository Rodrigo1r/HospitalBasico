package com.hospital.Modelo;

import java.util.List;


import javax.persistence.*;



@Entity
@Table(name = "historia_clinica")
public class HistoriaClinica extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "historia" , cascade = CascadeType.ALL)
    private List<DetalleClinico> detalles;

	public List<DetalleClinico> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleClinico> detalles) {
		this.detalles = detalles;
	}

	public HistoriaClinica(List<DetalleClinico> detalles) {
		super();
		this.detalles = detalles;
	}
    
    
    
}

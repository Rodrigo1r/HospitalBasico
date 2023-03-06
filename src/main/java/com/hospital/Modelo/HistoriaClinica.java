package com.hospital.Modelo;

import java.util.List;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "historia_clinica")
public class HistoriaClinica extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne(mappedBy = "historia" , cascade =CascadeType.ALL)
	private Persona persona;


	//fetch = FetchType.LAZY , mappedBy = "historia" , cascade =CascadeType.ALL)
    @OneToMany(mappedBy = "historia" , cascade =CascadeType.ALL)
    private List<DetalleClinico> detalle;

	
}

package com.hospital.Modelo;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name = "configurar_horario")
public class ConfigurarHorario extends Auditoria {

   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia_inicio_semana")
    private Integer dia_inicio_semana;

    @Column(name = "dia_fin_semana")
    private Integer dia_fin_semana;

    //@DateTimeFormat(pattern = "HH:mm")
    @Column(name = "hora_inicio_dia")
    private LocalTime hora_inicio_dia;

    //@DateTimeFormat(pattern = "HH:mm")
    @Column(name = "hora_fin_dia")
    private LocalTime hora_fin_dia;

    @Column(name = "hora_inicio_tarde")
    private LocalTime hora_inicio_tarde;

    //@DateTimeFormat(pattern = "HH:mm")
    @Column(name = "hora_fin_tarde")
    private LocalTime hora_fin_tarde;
    
   
    
    //@DateTimeFormat(pattern = "HH:mm")
    @Column(name = "duracion_cita")
    private LocalTime duracion_cita;
    
    @Column(name = "dias_planificacion")
    private Integer dias_planificacion;

    public ConfigurarHorario() {
    }


    
    
	public ConfigurarHorario(Integer dia_inicio_semana, Integer dia_fin_semana, LocalTime hora_inicio_dia,
			LocalTime hora_fin_dia, LocalTime hora_inicio_tarde, LocalTime hora_fin_tarde, LocalTime duracion_cita,
			Integer dias_planificacion) {
		this.dia_inicio_semana = dia_inicio_semana;
		this.dia_fin_semana = dia_fin_semana;
		this.hora_inicio_dia = hora_inicio_dia;
		this.hora_fin_dia = hora_fin_dia;
		this.hora_inicio_tarde = hora_inicio_tarde;
		this.hora_fin_tarde = hora_fin_tarde;
		this.duracion_cita = duracion_cita;
		this.dias_planificacion = dias_planificacion;
	}
	

	public Integer getDias_planificacion() {
		return dias_planificacion;
	}



	public void setDias_planificacion(Integer dias_planificacion) {
		this.dias_planificacion = dias_planificacion;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDia_inicio_semana() {
		return dia_inicio_semana;
	}

	public void setDia_inicio_semana(Integer dia_inicio_semana) {
		this.dia_inicio_semana = dia_inicio_semana;
	}

	public Integer getDia_fin_semana() {
		return dia_fin_semana;
	}

	public void setDia_fin_semana(Integer dia_fin_semana) {
		this.dia_fin_semana = dia_fin_semana;
	}

	public LocalTime getHora_inicio_dia() {
		return hora_inicio_dia;
	}

	public void setHora_inicio_dia(LocalTime hora_inicio_dia) {
		this.hora_inicio_dia = hora_inicio_dia;
	}

	public LocalTime getHora_fin_dia() {
		return hora_fin_dia;
	}

	public void setHora_fin_dia(LocalTime hora_fin_dia) {
		this.hora_fin_dia = hora_fin_dia;
	}

	public LocalTime getHora_inicio_tarde() {
		return hora_inicio_tarde;
	}

	public void setHora_inicio_tarde(LocalTime hora_inicio_tarde) {
		this.hora_inicio_tarde = hora_inicio_tarde;
	}

	public LocalTime getHora_fin_tarde() {
		return hora_fin_tarde;
	}

	public void setHora_fin_tarde(LocalTime hora_fin_tarde) {
		this.hora_fin_tarde = hora_fin_tarde;
	}

	public LocalTime getDuracion_cita() {
		return duracion_cita;
	}

	public void setDuracion_cita(LocalTime duracion_cita) {
		this.duracion_cita = duracion_cita;
	}

    
    
 
}

package com.hospital.Genericos;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IMedicosHorarios {
    public Long getId();

    public String getNombres();

    public String getApellidos();

    public Long getId_medico();

    public LocalDate getFecha_dia();

    public LocalTime getHorario();

    public Boolean getActivo();

    public Boolean getAsignado();


}



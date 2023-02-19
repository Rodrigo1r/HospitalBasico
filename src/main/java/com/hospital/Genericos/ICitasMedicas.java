package com.hospital.Genericos;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ICitasMedicas {

    public Long getId_horario();

    public String getNombres();

    public String getApellidos();

    public Long getId_cita();

    public Long getId_persona();

    public String getNombre_especialidad();

    public LocalTime getHorario();

    public Long getId_medico();

    public String getNombre_medico();

    public LocalDate getFecha_dia();

    public Boolean getEstado();

    public Integer getCantidad();
    public String getMes();

}

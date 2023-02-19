package com.hospital.Repositorio;

import com.hospital.Modelo.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EspecialidadRepositorio  extends JpaRepository<Especialidad,Long> {
     public Especialidad findByNombreEspecialidad(String nombreEspecialidad);
    List<Especialidad> findAllByEstado(Boolean estado);

    public Especialidad findById(long Id);

}

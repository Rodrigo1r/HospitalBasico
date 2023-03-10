package com.hospital.Repositorio;

import com.hospital.Modelo.Enfermedad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnfermedadRepositorio extends JpaRepository<Enfermedad, Long> {
}

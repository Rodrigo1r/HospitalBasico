package com.hospital.Repositorio;

import com.hospital.Modelo.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamenRepositorio extends JpaRepository<Examen,Long> {
}

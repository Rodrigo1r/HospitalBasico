package com.hospital.Repositorio;

import com.hospital.Modelo.Rol;
import com.hospital.Util.NombresRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolRepositorio extends JpaRepository<Rol,Long> {

    Optional <Rol> findByNombre(NombresRoles nombre);
    List<Rol> findAllByEstado(Boolean estado);

 

}

package com.hospital.Repositorio;

import com.hospital.Genericos.ICitasMedicas;
import com.hospital.Modelo.Especialidad;
import com.hospital.Util.NombresRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import com.hospital.Modelo.Persona;
import org.springframework.data.jpa.repository.Query;


public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
    public Persona findByUsuario(String usuario);

    public Persona findByIdentificacion(String identificacion);

    public Persona findByCorreo(String correo);

    public List<Persona> findAllByRoles(NombresRoles roles);

    public List<Persona> findAllByEspecialidades(Especialidad especialidad);

    @Query(value = "select to_char(a.fecha_creacion , 'TMMonth') as mes , count(a.id) as cantidad from " +
            "persona a GROUP by mes order by mes" , nativeQuery = true)
    public List<ICitasMedicas> totalPcaientesPorMes();

    @Query(value = "select count(a.id) as cantidad from " +
            "persona a where to_char(a.fecha_creacion , 'TMMonth') = to_char(CURRENT_DATE , 'TMMonth') "
            , nativeQuery = true)
    public Integer totalPacientesMesActual();


}

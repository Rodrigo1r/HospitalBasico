package com.hospital.Repositorio;

import com.hospital.Genericos.ICitasMedicas;
import com.hospital.Modelo.AtencionMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AtencionMedicaRepositorio  extends JpaRepository<AtencionMedica , Long> {



@Query ("Select count(a) from AtencionMedica a where a.fechaCreacion = CURRENT_DATE")
public Integer totalAtencionesHoy();

@Query(value = "select count(a.id) as atenciones from " +
"atencion_medica a where fecha_creacion = (CURRENT_DATE - 1)" , nativeQuery = true)
public Integer  totalAtencionesAyer();

@Query(value = "select count(a.id) as atenciones from " +
"atencion_medica a where to_char(a.fecha_creacion , 'TMMonth') = to_char(CURRENT_DATE , 'TMMonth')" , nativeQuery = true)
public Integer  totalAtencionesMesActual();

@Query(value = "select count(a.id) as atenciones from " +
"atencion_medica a where to_char(a.fecha_creacion , 'TMMonth') = to_char(CURRENT_DATE - interval '1 month' , 'TMMonth')" , nativeQuery = true)
public Integer  totalAtencionesMesAnterior();

@Query(value = "select count(a.id) as atenciones from " +
"atencion_medica a where to_char(a.fecha_creacion , 'TMYear') = to_char(CURRENT_DATE , 'TMYear')" , nativeQuery = true)
public Integer  totalAtencionesAnioActual();

@Query(value = "select count(a.id) as atenciones from " +
"atencion_medica a where to_char(a.fecha_creacion , 'TMYear') = to_char(CURRENT_DATE - interval '1 year', 'TMYear')" , nativeQuery = true)
public Integer  totalAtencionesAnioAnterior();

    @Query(value = "select to_char(a.fecha_creacion , 'TMMonth') as mes , count(a.id) as cantidad from " +
            "atencion_medica a GROUP by mes order by mes" , nativeQuery = true)
    public List<ICitasMedicas> totalAtencionesPorMes();



}

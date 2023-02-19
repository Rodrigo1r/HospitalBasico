package com.hospital.Repositorio;

import com.hospital.Genericos.IMedicosHorarios;
import com.hospital.Modelo.HorarioAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface
HorarioAtencionRepositorio extends JpaRepository<HorarioAtencion, Long> {

    @Query(value = "select a.fecha_dia , a.horario , a.activo , a.asignado , p.nombres , p.apellidos , a.id " +
            "from horario_atencion a , persona p , persona_horarios b where (p.id = b.persona_id AND " +
            "a.id = b.horarios_id) order by a.id", nativeQuery = true)
    public List<IMedicosHorarios> buscarMedicosHorarios();


    @Query(value = "select a.fecha_dia , a.horario , a.activo , a.asignado , p.nombres , p.apellidos , " +
            "a.id as horario_atencion_id , c.especialidades_id , p.id as medico_id " +
            "from horario_atencion a , persona p , persona_horarios b , persona_especialidades c " +
            "where (p.id = b.persona_id AND a.id = b.horarios_id AND c.persona_id = p.id) ", nativeQuery = true)
    public List<IMedicosHorarios> buscarMedicosHorariosEspecialidad();

    @Query(value = "select DISTINCT a.fecha_dia from horario_atencion a  " +
            ", persona_horarios b , persona c , persona_especialidades d " +
            "where ((b.horarios_id = a.id and b.persona_id = c.id and a.asignado = false)  " +
            "and (c.id = d.persona_id and a.fecha_dia >= CURRENT_DATE and " +
            "d.especialidades_id = :espeId)) order by a.fecha_dia", nativeQuery = true)
    public List<IMedicosHorarios> diaslibresPorEspecialidad(Long espeId);


    @Query(value = "select DISTINCT a.fecha_dia, c.nombres , c.apellidos , c.id from horario_atencion a  " +
            ", persona_horarios b , persona c , persona_especialidades d  " +
            "where ((b.horarios_id = a.id and b.persona_id = c.id and a.asignado = false)   " +
            "and (c.id = d.persona_id and d.especialidades_id = :espeId and a.fecha_dia = :fecha_dia)) " +
            "order by a.fecha_dia", nativeQuery = true)
    public List<IMedicosHorarios> medicosPorEspecialidadFechaDia(Long espeId, LocalDate fecha_dia);

    @Query(value = "select DISTINCT a.id , a.fecha_dia, a.horario, c.nombres , c.apellidos    " +
            "from horario_atencion a, persona_horarios b , persona c , persona_especialidades d  " +
            "where ((b.horarios_id = a.id and b.persona_id = c.id and a.asignado = false)   " +
            "and (c.id = d.persona_id and d.especialidades_id = :espeId and a.fecha_dia = :fecha_dia and " +
            "c.id = :idMedico and a.horario >= CURRENT_TIME))  order by a.fecha_dia", nativeQuery = true)
    public List<IMedicosHorarios> horariosPorEspecialidadFechaActualMedico(Long espeId,
                                                                        LocalDate fecha_dia,
                                                                        Long idMedico);

    @Query(value = "select DISTINCT a.id , a.fecha_dia, a.horario, c.nombres , c.apellidos    " +
            "from horario_atencion a, persona_horarios b , persona c , persona_especialidades d  " +
            "where ((b.horarios_id = a.id and b.persona_id = c.id and a.asignado = false)   " +
            "and (c.id = d.persona_id and d.especialidades_id = :espeId and a.fecha_dia = :fecha_dia and " +
            "c.id = :idMedico))  order by a.fecha_dia", nativeQuery = true)
    public List<IMedicosHorarios> horariosPorEspecialidadFechaSuperiorMedico(Long espeId,
                                                                        LocalDate fecha_dia,
                                                                        Long idMedico);

    @Query(value = "select max(fecha_dia) as fecha_dia FROM horario_atencion"
            , nativeQuery = true)
    public List<IMedicosHorarios> buscarUltimaFechaHorarioAtencion();


}
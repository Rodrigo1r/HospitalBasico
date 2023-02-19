package com.hospital.Repositorio;

import com.hospital.Genericos.ICitasMedicas;
import com.hospital.Modelo.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CitaMedicaRepositorio extends JpaRepository<CitaMedica, Long> {

    List<CitaMedica> findAllByEstado(Boolean estado);

    @Query(value = "select distinct a.fecha_dia, c.nombres , c.apellidos , e.id as id_cita , c.id as id_persona,  " +
            "e.id_medico , d.nombre_especialidad , a.id as id_horario , a.horario ,e.nombre_medico ,e.estado ,e.atencion " +
            "from horario_atencion a , persona_horarios b , persona c , especialidad d ,  " +
            "cita_medica e where (d.id = e.id_especialidad and c.id = e.id_persona and " +
            "a.id = e.id_horario and e.estado = true and e.atencion = :estadoAtencion) order by a.fecha_dia", nativeQuery = true)
    public List<ICitasMedicas> listadoCitasPorAtencion(Boolean estadoAtencion);


    @Query(value = "select d.nombre_especialidad , count(e.id) as cantidad  " +
            "from especialidad d , cita_medica e  " +
            "where (d.id = e.id_especialidad and e.estado = true and e.atencion = true)  " +
            "GROUP by d.nombre_especialidad  " +
            "order by d.nombre_especialidad", nativeQuery = true)
    public List<ICitasMedicas> listadoCitasAtendidasPorEspecialidad();



    @Query(value = "select e.nombre_medico , count(e.id) as cantidad  " +
            "from especialidad d , cita_medica e  " +
            "where (d.id = e.id_especialidad and e.estado = true and e.atencion = true)  " +
            "GROUP by e.nombre_medico  " +
            "order by e.nombre_medico", nativeQuery = true)
    public List<ICitasMedicas> listadoCitasAtendidasPorMedico();


    @Query(value = " select distinct to_char(a.fecha_dia , 'TMMonth') as mes  , count(e.id) as cantidad  " +
            " from horario_atencion a , persona c , especialidad d , " +
            "cita_medica e where (d.id = e.id_especialidad and c.id = e.id_persona and " +
            "a.id = e.id_horario and e.estado = true and e.atencion = true) " +
            "GROUP by mes ORDER by mes", nativeQuery = true)
    public List<ICitasMedicas> listadoCitasAtendidasPorMes();


    @Query(value = "select distinct a.fecha_dia, c.nombres , c.apellidos , e.id as id_cita , c.id as id_persona,  " +
            "e.id_medico , d.nombre_especialidad , a.id as id_horario , a.horario ,e.nombre_medico ,e.estado ,e.atencion " +
            "from horario_atencion a , persona_horarios b , persona c , especialidad d ,  " +
            "cita_medica e where (d.id = e.id_especialidad and c.id = e.id_persona and " +
            "a.id = e.id_horario and e.estado = true) order by a.fecha_dia", nativeQuery = true)
    public List<ICitasMedicas> listadoCitasAgendadas();



    @Query(value = "select d.nombre_especialidad , count(e.id) as cantidad  " +
            "from especialidad d , cita_medica e  " +
            "where (d.id = e.id_especialidad and e.estado = true)  " +
            "GROUP by d.nombre_especialidad  " +
            "order by d.nombre_especialidad", nativeQuery = true)
    public List<ICitasMedicas> listadoCitasAgendadasPorEspecialidad();

    @Query(value = "select e.nombre_medico , count(e.id) as cantidad  " +
            "from especialidad d , cita_medica e  " +
            "where (d.id = e.id_especialidad and e.estado = true)  " +
            "GROUP by e.nombre_medico  " +
            "order by e.nombre_medico", nativeQuery = true)
    public List<ICitasMedicas> listadoCitasAgendadasPorMedico();

    @Query(value = "  select distinct to_char(a.fecha_dia , 'TMMonth') as mes  , count(e.id) as cantidad " +
            " from horario_atencion a , " +
            "cita_medica e where (a.id = e.id_horario and e.estado = true) " +
            "GROUP by mes ORDER by mes ", nativeQuery = true)
    public List<ICitasMedicas> listadoCitasAgendadasPorMes();

    @Query(value = "select distinct a.fecha_dia, c.nombres , c.apellidos , e.id as id_cita , c.id as id_persona,  " +
            "e.id_medico , d.nombre_especialidad , a.id as id_horario , a.horario ,e.nombre_medico ,e.estado ,e.atencion " +
            "from horario_atencion a , persona_horarios b , persona c , especialidad d ,  " +
            "cita_medica e where (d.id = e.id_especialidad and c.id = e.id_persona and " +
            "a.id = e.id_horario and e.atencion = :estadoAtencion and e.estado = true and e.id_medico = :idMedico) order by a.fecha_dia", nativeQuery = true)
    public List<ICitasMedicas> listadoCitasPorMedicoAtencion(Long idMedico , Boolean estadoAtencion);

    @Query(value = "select distinct a.fecha_dia, c.nombres , c.apellidos , e.id as id_cita , c.id as id_persona,  " +
            "e.id_medico , d.nombre_especialidad , a.id as id_horario , a.horario ,e.nombre_medico ,e.estado ,e.atencion " +
            "from horario_atencion a , persona_horarios b , persona c , especialidad d ,  " +
            "cita_medica e where (d.id = e.id_especialidad and c.id = e.id_persona and " +
            "a.id = e.id_horario and e.atencion = :estadoAtencion and e.estado = true and id_persona = :idpersona) order by a.fecha_dia", nativeQuery = true)
    public List<ICitasMedicas> listadoCitasPorPaciente(Long idpersona , Boolean estadoAtencion);

    @Query(value = "select distinct a.fecha_dia, c.nombres , c.apellidos , e.id as id_cita , c.id as id_persona,  " +
            "e.id_medico , d.nombre_especialidad , a.id as id_horario , a.horario ,e.nombre_medico ,e.estado ,e.atencion " +
            "from horario_atencion a , persona_horarios b , persona c , especialidad d ,  " +
            "cita_medica e where (d.id = e.id_especialidad and c.id = e.id_persona and " +
            "a.id = e.id_horario and e.atencion = false and e.estado = true and id_persona = :idpersona " +
            "and e.id_especialidad = :idEspecialidad and a.fecha_dia = :fecha) order by a.fecha_dia", nativeQuery = true)
    public List<ICitasMedicas> buscaCitasPacienteEspecialidadDia(Long idpersona , Long idEspecialidad, LocalDate fecha);

    @Query(value = "select distinct a.fecha_dia, c.nombres , c.apellidos , e.id as id_cita , c.id as id_persona,  " +
            "e.id_medico , d.nombre_especialidad , a.id as id_horario , a.horario ,e.nombre_medico ,e.estado ,e.atencion " +
            "from horario_atencion a , persona_horarios b , persona c , especialidad d ,  " +
            "cita_medica e where (d.id = e.id_especialidad and c.id = e.id_persona and " +
            "a.id = e.id_horario and e.atencion = false and e.estado = true and id_persona = :idpersona " +
            "and a.fecha_dia = :fecha) order by a.fecha_dia", nativeQuery = true)
    public List<ICitasMedicas> buscaCitasPacientePorDia(Long idpersona , LocalDate fecha);

    @Query(value = "select * from " +
            "cita_medica e where e.id_horario = :id_horario " , nativeQuery = true)
    public List<CitaMedica> buscarCitaPorId_Horario(Long id_horario);

    @Query(value = "select count(e.id_horario) as citas from " +
            "cita_medica e , horario_atencion a where e.id_horario = a.id and " +
           "e.estado = true and a.fecha_dia = CURRENT_DATE" , nativeQuery = true)
    public Integer  totalCitasHoy();

    @Query(value = "select count(e.id_horario) as citas from " +
            "cita_medica e , horario_atencion a where e.id_horario = a.id and " +
            "e.estado = true and a.fecha_dia = (CURRENT_DATE - 1)" , nativeQuery = true)
    public Integer  totalCitasAyer();

    @Query(value = "select count(e.id_horario) as citas from " +
            "cita_medica e , horario_atencion a where e.id_horario = a.id " +
            "and to_char(a.fecha_dia , 'TMMonth') = to_char(CURRENT_DATE , 'TMMonth')" , nativeQuery = true)
    public Integer  totalCitasMesActual();

    @Query(value = "select count(e.id_horario) as citas from " +
            "cita_medica e , horario_atencion a where e.id_horario = a.id " +
            "and to_char(a.fecha_dia , 'TMMonth') = to_char(CURRENT_DATE - interval '1 month' , 'TMMonth')" , nativeQuery = true)
    public Integer  totalCitasMesAnterior();

    @Query(value = "select count(e.id_horario) as citas from " +
            "cita_medica e , horario_atencion a where e.id_horario = a.id " +
            "and to_char(a.fecha_dia , 'TMYear') = to_char(CURRENT_DATE , 'TMYear')" , nativeQuery = true)
    public Integer  totalCitasAnioActual();

    @Query(value = "select count(e.id_horario) as citas from " +
            "cita_medica e , horario_atencion a where e.id_horario = a.id " +
            "and to_char(a.fecha_dia , 'TMYear') = to_char(CURRENT_DATE - interval '1 year' , 'TMYear')" , nativeQuery = true)
    public Integer  totalCitasAnioAnterior();

    @Query(value = "select to_char(a.fecha_dia , 'TMMonth') as mes , count(e.id_horario) as cantidad from  " +
            "cita_medica e , horario_atencion a where e.id_horario = a.id " +
            "group by mes order by mes" , nativeQuery = true)
    public List<ICitasMedicas>  totalCitasPorMes();

    @Query(value = "select d.nombre_especialidad , count(e.id) as cantidad  " +
            "from especialidad d , cita_medica e ,horario_atencion a  " +
            "where (d.id = e.id_especialidad and e.estado = true and a.id = e.id_horario  " +
            "and to_char(a.fecha_dia , 'TMMonth') = to_char(CURRENT_DATE , 'TMMonth') )GROUP by d.nombre_especialidad  " +
            "order by d.nombre_especialidad", nativeQuery = true)
    public List<ICitasMedicas> citasAgendadasPorEspecialidadMesActual();

}

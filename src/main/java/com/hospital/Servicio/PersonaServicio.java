package com.hospital.Servicio;

import java.util.ArrayList;
import java.util.List;


import com.hospital.Genericos.ICitasMedicas;
import com.hospital.Modelo.Especialidad;
import com.hospital.Modelo.HorarioAtencion;
import com.hospital.Modelo.Rol;
import com.hospital.Util.NombresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.hospital.Modelo.Persona;
import com.hospital.Repositorio.PersonaRepositorio;


@Service
public class PersonaServicio {

    @Autowired
    private PersonaRepositorio personaRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Persona insertar(Persona persona) {
        persona.setEstado(true);
        persona.setPassword(passwordEncoder.encode(persona.getPassword()));
        return personaRepo.save(persona);
    }

    public Persona actualizarPersona(Persona persona) {
        var pAnterior = personaRepo.findById(persona.getId());

        var validaPass = pAnterior.get().getPassword().isEmpty();
        if((persona.getPassword().isEmpty() && !validaPass) ){
            persona.setPassword(pAnterior.get().getPassword());
        }

        else{
            persona.setPassword(passwordEncoder.encode(persona.getPassword()));
        }
        var validaEspe = pAnterior.get().getEspecialidades();
        if(persona.getEspecialidades() == null &&  validaEspe.size() > 0){
            persona.setEspecialidades(validaEspe);
        }

        var validaHorario = pAnterior.get().getHorarios();
        if(persona.getHorarios() == null &&  validaHorario.size() > 0){
            persona.setHorarios(validaHorario);
        }

        return personaRepo.save(persona);
    }

    public Persona actualizarMedico(Persona persona) {
        var pAnterior = personaRepo.findById(persona.getId());

        var validaEspe = pAnterior.get().getEspecialidades();
        if(persona.getEspecialidades() == null &&  validaEspe.size() > 0){
            persona.setEspecialidades(pAnterior.get().getEspecialidades());
        }


        return personaRepo.save(persona);
    }


    public List<Persona> listarTodos() {
        return personaRepo.findAll();
    }

    public void eliminar(Persona persona) {
        personaRepo.delete(persona);
    }


    public Persona buscaPersonaPorId(Long id) {
        var persona = personaRepo.findById(id);
        if (persona.isPresent()) {
            return persona.get();
        }
        return null;
    }

    public void eliminarPorId(Long id) {
        personaRepo.deleteById(id);
    }

    public Persona buscaPersonaPorUsuario(String usuario) {
        var bUsuario = personaRepo.findByUsuario(usuario);
        if (bUsuario != null) {
            return bUsuario;
        }
        return null;

    }

    public String buscarPersona(Persona persona) {
        var bUsuario = personaRepo.findByUsuario(persona.getUsuario());
        var bIdentifi = personaRepo.findByIdentificacion(persona.getIdentificacion());
        var bCorreo = personaRepo.findByCorreo(persona.getCorreo());

        if (bIdentifi != null) {
            return "Identificacion ya existe en la base de datos";
        } else if (bUsuario != null) {
            return "Usuario ya existe en la base de datos";
        } else if (bCorreo != null) {
            return "Correo ya existe en la base de datos";
        }

        return null;
    }


    public Long count() {
        return personaRepo.count();
    }

    public List<Persona> listarPersonasPorRol(NombresRoles roles) {
        List<Persona> lista = personaRepo.findAll();

        List<Persona> listaMed = new ArrayList<Persona>();
        for (Persona persona : lista) {
            for (Rol rol : persona.getRoles()) {
                if (rol.getNombre() == roles) {
                    listaMed.add(persona);
                }
            }
        }

        return listaMed;
    }

    public List<Persona> listarPersonasPorEspecialidad(Long id) {
        List<Persona> lista = personaRepo.findAll();

        List<Persona> listaPorEspecialidad = new ArrayList<Persona>();
        for (Persona persona : lista) {
            for (Especialidad especialidad : persona.getEspecialidades()) {
                if (especialidad.getId() == id) {
                    listaPorEspecialidad.add(persona);
                }
            }
        }

        return listaPorEspecialidad;
    }

    public void agregaHorarios(List<Persona> listaMedicos, List<HorarioAtencion> horarios) {
        List<HorarioAtencion> agrHorarios;
        for (Persona persona : listaMedicos) {
            agrHorarios = new ArrayList<>();
            if(persona.getHorarios().size()>0){
                agrHorarios.addAll(persona.getHorarios());
            }
            agrHorarios.addAll(horarios);
            persona.setHorarios(agrHorarios);
            personaRepo.save(persona);
        }

    }

    public Persona buscarPersonaPorIdentificacion(String identificacion) {
        var persona = personaRepo.findByIdentificacion(identificacion);

        if (persona != null) {
            return persona;
        }
        return null;


    }

    public void actualizaHorarioAsignadoMedico(Long idMedico, Long idHorario, Boolean asigna) {
        var medico = personaRepo.findById(idMedico);

        if (medico != null) {

            /*
            for (HorarioAtencion horarios : medico.get().getHorarios()) {
                if (horarios.getId() == idHorario) {
                    horarios.setAsignado(asigna);
                }
            }
            */
            medico.get().getHorarios().forEach(x->{
                var valor = x.getId();
               if(  valor == idHorario ){
                   x.setAsignado(asigna);
               }
            });

            personaRepo.save(medico.get());

        }

    }

    public List<ICitasMedicas> totalPacientesPorMes(){
        return personaRepo.totalPcaientesPorMes();
    }

    public Integer totalPacientesMesActual(){
        return personaRepo.totalPacientesMesActual();
    }

    public List<Persona> buscaPersonaPorApellidos(String term){
    	return personaRepo.buscaPersonaPorApellidos(term);
    }
    
    public List<Persona> findByApellidosLikeIgnoreCase(String term){
    	return personaRepo.findByApellidosLikeIgnoreCase("%" + term + "%");
    }

    
}

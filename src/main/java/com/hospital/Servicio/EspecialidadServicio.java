package com.hospital.Servicio;

import com.hospital.Modelo.Especialidad;
import com.hospital.Repositorio.EspecialidadRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadServicio {

    @Autowired
    EspecialidadRepositorio especialidadRepositorio;

    public Especialidad insertar(Especialidad especialidad) {
        return especialidadRepositorio.save(especialidad);
    }

    public Especialidad actualizar(Especialidad especialidad) {
        return especialidadRepositorio.save(especialidad);
    }

    public List<Especialidad> listar() {
        return especialidadRepositorio.findAll();
    }

    public void eliminarEspecialidad(Especialidad especialidad) {
        especialidad.setEstado(false);
        especialidadRepositorio.save(especialidad);

    }

    public void eliminarEspecialidadPorNombre(String nombre) {
        var buscaEspe = especialidadRepositorio.findByNombreEspecialidad(nombre);
        buscaEspe.setEstado(false);
        especialidadRepositorio.save(buscaEspe);

    }
    public void eliminarEspecialidadPorId(Long id) {
        var buscaEspe = buscarEspecialidadPorId(id);
        buscaEspe.setEstado(false);
        especialidadRepositorio.save(buscaEspe);

    }



    public Especialidad buscarEspecialidadPorId(Long id) {
        var buscaEspe = especialidadRepositorio.findById(id);

        if(buscaEspe.isPresent()){
            return  buscaEspe.get();
        }
        return null;
    }



    public boolean buscaEspecialidadPorNombre(String nombreEspecialidad) {
        var buscaEspe = especialidadRepositorio.findByNombreEspecialidad(nombreEspecialidad);
        if( buscaEspe !=null) {
            return true;
        }
        return false;
    }

    public Long count(){
        return especialidadRepositorio.count();

    }

    public List<Especialidad> buscaEspecialidadPorEstado(Boolean estado){
        var listaEspe = especialidadRepositorio.findAllByEstado(estado);
        if(listaEspe.isEmpty()){
            return null;
        }
        return listaEspe;
    }



}

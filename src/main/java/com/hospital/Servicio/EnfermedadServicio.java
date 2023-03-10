package com.hospital.Servicio;

import com.hospital.Modelo.Enfermedad;
import com.hospital.Repositorio.EnfermedadRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnfermedadServicio {


    @Autowired
    EnfermedadRepositorio enfermedadRpt;

    public Enfermedad insertar(Enfermedad enfermedad) {
        return enfermedadRpt.save(enfermedad);
    }

    public Enfermedad actualizar(Enfermedad enfermedad) {
        return enfermedadRpt.save(enfermedad);
    }

    public List<Enfermedad> listar() {
        return enfermedadRpt.findAll();
    }

    public void eliminarEspecialidad(Enfermedad enfermedad) {

        enfermedadRpt.save(enfermedad);

    }

    public  Enfermedad buscarEnfermedadPorId(Long id){
        var enfermedad = enfermedadRpt.findById(id);
        if(enfermedad.isPresent()){
            return enfermedad.get();
        }
        return null;

    }

    public void eliminarPorId(Long id) {
        enfermedadRpt.deleteById(id);
    }



}

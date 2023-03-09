package com.hospital.Servicio;

import com.hospital.Modelo.Examen;
import com.hospital.Modelo.Receta;
import com.hospital.Repositorio.ExamenRepositorio;
import com.hospital.Repositorio.RecetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaServicio {

    @Autowired
    RecetaRepositorio   recetaRpt;

    public Receta insertar(Receta receta) {
        return recetaRpt.save(receta);
    }

    public Receta actualizar(Receta receta) {
        return recetaRpt.save(receta);
    }

    public List<Receta> listar() {
        return recetaRpt.findAll();
    }

    public void eliminarReceta(Receta receta) {
        //especialidad.setEstado(false);
        recetaRpt.delete(receta);

    }
}

package com.hospital.Servicio;



import com.hospital.Modelo.Examen;
import com.hospital.Repositorio.ExamenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamenServicio {

    @Autowired
    ExamenRepositorio examenRpt;

    public Examen insertar(Examen examen) {
        return examenRpt.save(examen);
    }

    public Examen actualizar(Examen examen) {
        return examenRpt.save(examen);
    }

    public List<Examen> listar() {
        return examenRpt.findAll();
    }

    public void eliminarExamen(Examen examen) {
        //especialidad.setEstado(false);
        examenRpt.delete(examen);

    }

    public List<String> motivosExamen(){
        List<String> lista = new ArrayList<String>() ;
        lista.add("Exámenes de Laboratorio");
        lista.add("Radiografía");
        lista.add("Rayos X");
        lista.add("Endoscopia");
        lista.add("Tomografía");

        return lista;
    }
}

package com.hospital.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.Modelo.HistoriaClinica;
import com.hospital.Repositorio.HistoriaClinicaRepositorio;

@Service
public class HistoriaClinicaServicio {

    @Autowired
    HistoriaClinicaRepositorio historiaClinicaRpt;

    public HistoriaClinica insertar(HistoriaClinica historiaClinica) {
        //atencionMedica.setFechaAtencion(LocalDate.now());
        return historiaClinicaRpt.save(historiaClinica);
    }

    public HistoriaClinica grabar(HistoriaClinica historiaClinica) {
        
            return historiaClinicaRpt.save(historiaClinica);
      
        
    }


}

package com.hospital.Servicio;

import com.hospital.Genericos.ICitasMedicas;
import com.hospital.Modelo.AtencionMedica;
import com.hospital.Modelo.HistoriaClinica;
import com.hospital.Repositorio.AtencionMedicaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtencionMedicaServicio {
    @Autowired
    AtencionMedicaRepositorio atencionMedicaRpt;

    public AtencionMedica insertar(AtencionMedica atencionMedica) {
        //atencionMedica.setFechaAtencion(LocalDate.now());
        return atencionMedicaRpt.save(atencionMedica);
    }

    public AtencionMedica actualizar(AtencionMedica atencionMedica) {
        return atencionMedicaRpt.save(atencionMedica);
    }

    public List<AtencionMedica> listar() {
        return atencionMedicaRpt.findAll();
    }

    public List<String> motivosConsulta(){
        List<String> lista = new ArrayList<String>() ;
        lista.add("Consulta");
        lista.add("Revisión Examenes");
        lista.add("Limpieza");
        lista.add("Extracción");

        return lista;
    }

    public Integer atencionesHoy(){
        return atencionMedicaRpt.totalAtencionesHoy();
    }

    public Integer atencionesAyer(){
        return atencionMedicaRpt.totalAtencionesAyer();
    }

    public Integer atencionesMesActual(){
        return atencionMedicaRpt.totalAtencionesMesActual();
    }

    public Integer atencionesMesAnterior(){
        return atencionMedicaRpt.totalAtencionesMesAnterior();
    }

    public Integer atencionesAnioActual(){
        return atencionMedicaRpt.totalAtencionesAnioActual();
    }

    public Integer atencionesAnioAnterior(){
        return atencionMedicaRpt.totalAtencionesAnioAnterior();
    }

    public List<ICitasMedicas> totalAtencionesPorMes(){
        return atencionMedicaRpt.totalAtencionesPorMes();
    }

    public AtencionMedica buscaAtencionPorIdCIta(Long idCIta){
        return atencionMedicaRpt.findAtencionMedicaByCitaMedica(idCIta);
    }

    public List<ICitasMedicas> listaHistoriaPorIdPaciente(HistoriaClinica hc) {
        if (hc != null) {
            return atencionMedicaRpt.listaHistoriaPorIdPaciente(hc.getId());
        }
        return null;
    }

    public List<ICitasMedicas> detalleAtencionMesCantidad(){
        return atencionMedicaRpt.detalleAtencionMesCantidad();
    }

}

package com.hospital.Servicio;


import com.hospital.Modelo.ConfigurarHorario;
import com.hospital.Repositorio.ConfigurarHorarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurarHorarioServicio {

    @Autowired
    ConfigurarHorarioRepositorio configurarHorarioRepo;

    public ConfigurarHorario insertar(ConfigurarHorario configurarHorario) {
        return configurarHorarioRepo.save(configurarHorario);
    }

    public ConfigurarHorario actualizar(ConfigurarHorario configurarHorario) {
        return configurarHorarioRepo.save(configurarHorario);
    }

    public void eliminarPorId(Long id) {
        configurarHorarioRepo.deleteById(id);
    }


    public List<ConfigurarHorario> listar() {
        return configurarHorarioRepo.findAll();
    }


    public ConfigurarHorario buscarConfiguracionPorId(Long id) {
        var buscaEspe = configurarHorarioRepo.findById(id);

        if (buscaEspe.isPresent()) {
            return buscaEspe.get();
        }
        return null;
    }


    public Long count() {
        return configurarHorarioRepo.count();

    }


}

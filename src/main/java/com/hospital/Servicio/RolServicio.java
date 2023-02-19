package com.hospital.Servicio;

import com.hospital.Modelo.Rol;
import com.hospital.Repositorio.RolRepositorio;
import com.hospital.Util.NombresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolServicio {

    @Autowired
    private RolRepositorio rolRepositorio;

    public Rol addRolPaciente() {
    	var opRol= rolRepositorio.findByNombre(NombresRoles.PACIENTE);
    	if(opRol.isPresent()) {
    		return rolRepositorio.save(opRol.get());
    	}
    	
    	return rolRepositorio.save(new Rol(NombresRoles.PACIENTE,true));
    }
    
    public Rol addRolSecretaria() {
    	var opRol= rolRepositorio.findByNombre(NombresRoles.SECRETARIA);
    	if(opRol.isPresent()) {
    		return rolRepositorio.save(opRol.get());
    	}
        return rolRepositorio.save(new Rol(NombresRoles.SECRETARIA,true));
    }

    
    public Rol addRolMedico() {
    	var opRol= rolRepositorio.findByNombre(NombresRoles.MEDICO);
    	if(opRol.isPresent()) {
    		return rolRepositorio.save(opRol.get());
    	}
        return rolRepositorio.save(new Rol(NombresRoles.MEDICO,true));
    }


    /*
    public Rol addRolAdministrador() {
    	var opRol= rolRepositorio.findByNombre(NombresRoles.ADMINISTRADOR);
    	if(opRol.isPresent()) {
    		return rolRepositorio.save(opRol.get());
    	}
        return rolRepositorio.save(new Rol(NombresRoles.ADMINISTRADOR,true));
    }

     */

    
    public Rol addRolAltaGerencia() {
    	var opRol= rolRepositorio.findByNombre(NombresRoles.ALTAGERENCIA);
    	if(opRol.isPresent()) {
    		return rolRepositorio.save(opRol.get());
    	}
        return rolRepositorio.save(new Rol(NombresRoles.ALTAGERENCIA,true));
    }
    
    public Long count(){
        return rolRepositorio.count();

    }
    
    public List<Rol> saveAll(Iterable<Rol> entities){
		return rolRepositorio.saveAll(entities);
    	
    }
    
   public String ObtenerRolporID(Long id) {
	   return rolRepositorio.findById(id).get().getNombre().toString();
   }


    public Rol obtenerRolPorNombre(NombresRoles nombre){
        var opRol = rolRepositorio.findByNombre(nombre);
        if (opRol.isPresent()){
            return  opRol.get();
        }
        return  null;
    }
   
   public List<Rol> obtenerRolesActivos() {
	   return rolRepositorio.findAllByEstado(true);
   }

}

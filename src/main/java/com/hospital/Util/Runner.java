package com.hospital.Util;


import com.hospital.Modelo.Persona;
import com.hospital.Modelo.Rol;
import com.hospital.Servicio.PersonaServicio;
import com.hospital.Servicio.RolServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	private RolServicio rolServicio;

	@Autowired
	private PersonaServicio personaServicio;


	@Override
	public void run(String... args) throws Exception {

		if (this.rolServicio.count() == 0) {
			this.rolServicio.saveAll(List.of(rolServicio.addRolPaciente(), rolServicio.addRolSecretaria(),
					rolServicio.addRolMedico(), rolServicio.addRolAltaGerencia()

			));
		}

			Persona buscarPer = personaServicio.buscaPersonaPorUsuario("rordonez");

		if(buscarPer == null){

			Persona persona = new Persona();
			persona.setIdentificacion("0918934852");
			persona.setNombres("Rodrigo");
			persona.setApellidos("Ordoñez");
			persona.setCorreo("Rodrigo1r@hotmail.com");
			persona.setTelefono("0986937694");
			persona.setGenero("Masculino");
			persona.setLugar_residencia("Guayaquil");
			String fechaString = "1985-05-01";
			SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaNorm = fechaFormato.parse(fechaString);
			persona.setFecha_nacimiento(fechaNorm);
			persona.setUsuario("rordonez");
			persona.setPassword("administrador");

			persona.setRoles(List.of( new  Rol(NombresRoles.ADMINISTRADOR,true)));


			personaServicio.insertar(persona);

		}





	}
}

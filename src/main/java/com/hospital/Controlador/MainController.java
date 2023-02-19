package com.hospital.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	

	@GetMapping("/error-403")
	public String error403() {
		
		return "error/error-403";
	}
	
	@GetMapping("/error-404")
	public String error404() {
		
		return "error/404";
	}






	@GetMapping({"/welcome"})
	public String welcome(@RequestParam(name="nombre",required=false,defaultValue = "Rodrigo")String nombre, Model model) {
		
		model.addAttribute("nombre", nombre);
		return "listado";
		
	}

}

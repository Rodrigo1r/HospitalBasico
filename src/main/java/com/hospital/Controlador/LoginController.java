package com.hospital.Controlador;

import com.hospital.Servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@SessionAttributes("dpersona")
@Controller
public class LoginController {

	@Autowired
	PersonaServicio personaServicio;

	@GetMapping("/")
	public String inicio(Principal principal, RedirectAttributes flash) {

		if(principal != null) {
			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
			return "redirect:/home";
		}
		
		return "form-login";
	}

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth !=null){
			var persona = personaServicio.buscaPersonaPorUsuario(auth.getName());
			session.setAttribute("datoUser",persona );
		}
		
		model.addAttribute("dpersona",session.getAttribute("datoUser"));
		return "home/home";
	}


	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error,
						@RequestParam(value="logout", required = false) String logout,
						Model model, Principal principal, RedirectAttributes flash) {

		
		if(principal != null) {
			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
			return "redirect:/home";
		}
		 

		if(error != null) {
			model.addAttribute("error", "Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}

		if(logout != null) {
			model.addAttribute("logout", "Ha cerrado sesión con éxito!");
		}

		return "form-login";
	}
	
	
	
	@GetMapping("/logout")
	public String logoutPage () {
	    SecurityContextHolder.getContext().setAuthentication(null);
	    return "redirect:/login?logout";
	}
	
	/*
	 	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	 */
	 

}

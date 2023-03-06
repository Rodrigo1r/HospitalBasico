package com.hospital.WebConfig;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
public class LoginSuccess  {

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	LoginSuccesHandler succesHandler;
	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 try{
	        http.authorizeRequests().antMatchers("/", "/assets/**", "/persona/registro").permitAll()
					// Para que solo un usuario logueado con esos roles acceda a /home
					//.antMatchers("/home").access("hasRole('[PACIENTE]') or hasRole('[ADMINISTRADOR]')")
					.antMatchers("/home").hasAnyAuthority("PACIENTE","ADMINISTRADOR" , "MEDICO","SECRETARIA")
					// Para que solo un usuario logueado con esos roles vea la lista de productos
					.antMatchers("/persona/agregar").hasAnyAuthority("ADMINISTRADOR", "SECRETARIA")
					.antMatchers("/persona/editar/**").hasAnyAuthority("ADMINISTRADOR", "SECRETARIA")
					// Para que solo un usuario con ROLE_ADMIN pueda crear un producto
					.antMatchers("/persona/listar/**").hasAnyAuthority("ADMINISTRADOR", "SECRETARIA")
					.antMatchers("/atencion/**").hasAnyAuthority("ADMINISTRADOR", "MEDICO")
					.antMatchers("/especialidad/**").hasAnyAuthority("ADMINISTRADOR", "SECRETARIA")

					// Lógica del login (configurado en LoginSucessHandler)
					.and().formLogin()
					.successHandler(succesHandler)
					.loginPage("/login")
					.loginProcessingUrl("/login")
					// Si el login es exitoso, retorna a /home
					.defaultSuccessUrl("/home").permitAll().
					and().logout().logoutSuccessUrl("/login").permitAll()
					// Si el usuario va a una ruta sin acceso, devuelve a /error_403 (Configurado en MvcConfig)
					.and().exceptionHandling()
					.accessDeniedPage("/error-403");
					
	
			
			 /*
			 http.authorizeRequests().antMatchers("/", "/assets/**", "/persona/registro").permitAll()
	                //.antMatchers("/index").hasAnyRole("ADMIN","USER")
	                .anyRequest().authenticated()
	                .and()
	                .formLogin()
	                .loginPage("/login")

	                .permitAll()
	                .and()
	                .logout()
	                .invalidateHttpSession(true)
	                .clearAuthentication(true)
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                .logoutSuccessUrl("/")
	                .permitAll();
			 */
		 }
		 catch (Exception e){
			 System.out.println(e.getMessage());
		 }

	        return http.build();
	    }



}

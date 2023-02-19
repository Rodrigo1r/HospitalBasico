package com.hospital.Servicio;

import com.hospital.Modelo.Rol;
import com.hospital.Repositorio.PersonaRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    private Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optUser = personaRepositorio.findByUsuario(username);
        if(optUser != null){
            //Nuevo Codigo
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (Rol rol : optUser.getRoles()){
            authorities.add(new SimpleGrantedAuthority(rol.getNombre().name()));
            }

            if(authorities.isEmpty()){
                logger.error("Error login: El usuario  '" + username + "' no tiene roles asignados");
                throw  new UsernameNotFoundException("Error login: El usuario  '"  + username + "' no tiene roles asignados");
            }

            var nuevoUser = new User(optUser.getUsuario(), optUser.getPassword(), optUser.getEstado(),true,true,true,authorities);

            /*
            Codigo anterior
                  	var nuevoUser = new SecurityUser(optUser);
                  	 return nuevoUser;
            System.out.println(nuevoUser.getUsername());
        	System.out.println(nuevoUser.getPassword());
        	System.out.println(optUser.getRoles().get(0).getNombre().name());

             */


            return nuevoUser;
        }
        logger.error("Error login: no existe el usuario  '" + username + "'");
        throw  new UsernameNotFoundException("Usuario no encontrado: " + username);

    }

   
}

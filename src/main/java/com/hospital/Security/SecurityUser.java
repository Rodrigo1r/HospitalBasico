package com.hospital.Security;

import com.hospital.Modelo.Persona;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class SecurityUser implements UserDetails {


	private static final long serialVersionUID = 1L;
	
	private final Persona persona;
	

    public SecurityUser(Persona persona) {
		this.persona = persona;
	}

    @Override
    public String getPassword() {
        return persona.getPassword();
    }

    @Override
    public String getUsername() {
        return persona.getUsuario();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return persona.getRoles().stream().map(SecurityRoles::new).toList();
        
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	
    	authorities.add(new SimpleGrantedAuthority(persona.getRoles().toString()));
    	return authorities;
    	
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    
    
}

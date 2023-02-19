package com.hospital.Security;

import com.hospital.Modelo.Rol;
import org.springframework.security.core.GrantedAuthority;


public class SecurityRoles implements GrantedAuthority {

 
	private static final long serialVersionUID = 1L;
	
	private  final Rol rol;
    
	public SecurityRoles(Rol rol) {
		this.rol = rol;
	}
    

    @Override
    public String getAuthority() {
        return rol.getNombre().toString();
    }


    
}

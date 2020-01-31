package com.kiko.proyectoVacaciones.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kiko.proyectoVacaciones.model.Employee;
import com.kiko.proyectoVacaciones.repository.EmployeeRepository;



@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	EmployeeRepository repositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee usuario = repositorio.findFirstByEmail(username);
	
		UserBuilder builder = null;
		
		if (usuario != null & usuario.getRole().equals("T")) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(usuario.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {builder = User.withUsername(username);
		builder.disabled(false);
		builder.password(usuario.getPassword());
		builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
			
		}
		
		return builder.build();
	}

}

package com.kiko.proyectoVacaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiko.proyectoVacaciones.model.Developer;
import com.kiko.proyectoVacaciones.repository.DeveloperRepository;


@Service
public class DeveloperService {
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	DeveloperRepository repositorio;
	
	
	public Developer insertarDeveloper(Developer developer) {
		developer.setPassword(passwordEncoder.encode(developer.getPassword()));
		return repositorio.save(developer);
	}
	
	public List<Developer> buscarLosPorEmail(String email) {
		return repositorio.findByEmail(email);
	}

	public Developer buscarPorEmail(String email) {
		return repositorio.findFirstByEmail(email);
	}
	
	public void updateDeveloperWithEvent(Developer developer) {
		repositorio.save(developer);
	}

}

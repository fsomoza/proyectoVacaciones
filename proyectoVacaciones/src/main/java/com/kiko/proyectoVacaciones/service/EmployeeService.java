package com.kiko.proyectoVacaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiko.proyectoVacaciones.model.Employee;
import com.kiko.proyectoVacaciones.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repositorio;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	public Employee registrar(Employee e) {
		e.setPassword(passwordEncoder.encode(e.getPassword()));
		return repositorio.save(e);
	}
	
	public Employee findById(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	public Employee buscarPorEmail(String email) {
		return repositorio.findFirstByEmail(email);
	}

}

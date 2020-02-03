package com.kiko.proyectoVacaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiko.proyectoVacaciones.model.Developer;
import com.kiko.proyectoVacaciones.model.Employee;

public interface DeveloperRepository extends JpaRepository<Developer,Long> {
	
	Developer findByidEmployee(long id);
	Developer findFirstByEmail(String email);
	
	
	 

}

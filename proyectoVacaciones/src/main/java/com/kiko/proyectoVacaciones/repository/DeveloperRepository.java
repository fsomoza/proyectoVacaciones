package com.kiko.proyectoVacaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiko.proyectoVacaciones.model.Developer;

public interface DeveloperRepository extends JpaRepository<Developer,Long> {
	
	Developer findByidEmployee(long id);
	
	Developer findFirstByEmail(String email);
	
	List<Developer> findByEmail(String email);

}

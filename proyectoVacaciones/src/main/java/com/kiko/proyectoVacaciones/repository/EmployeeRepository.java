package com.kiko.proyectoVacaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiko.proyectoVacaciones.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	
	Employee findFirstByEmail(String email);

}

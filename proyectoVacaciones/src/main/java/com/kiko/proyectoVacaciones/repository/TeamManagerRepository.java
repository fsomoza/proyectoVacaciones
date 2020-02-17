package com.kiko.proyectoVacaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kiko.proyectoVacaciones.model.Developer;
import com.kiko.proyectoVacaciones.model.TeamManager;

public interface TeamManagerRepository extends JpaRepository<TeamManager,Long> {
	
	@Query("SELECT t.developers from TeamManager t WHERE t.idEmployee = ?1")
	List<Developer> findDeveloperByTmId(long id);
	
	TeamManager findFirstByEmail(String email);
	
	
	
	
	@Query("SELECT d from Developer d where d.teamManager.idEmployee = ?1 AND d.event.color = '#ffe599' ")
	List<Developer> findMyDevelopersById(long id);

}

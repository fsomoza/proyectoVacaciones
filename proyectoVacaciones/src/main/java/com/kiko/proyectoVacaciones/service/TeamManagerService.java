package com.kiko.proyectoVacaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiko.proyectoVacaciones.model.Developer;
import com.kiko.proyectoVacaciones.model.TeamManager;
import com.kiko.proyectoVacaciones.repository.TeamManagerRepository;

@Service
public class TeamManagerService {
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	TeamManagerRepository tmRepository;
	
	public TeamManager findTeamManager(String email) {
		 return tmRepository.findFirstByEmail(email);
	}
	
	public TeamManager insertTeamManager(TeamManager teamManager){
		teamManager.setPassword(passwordEncoder.encode(teamManager.getPassword()));
		return tmRepository.save(teamManager);

}
	
	
	public List<Developer> findTeamManagers(long id){
		return tmRepository.findDeveloperByTmId(id);
	}
	
}

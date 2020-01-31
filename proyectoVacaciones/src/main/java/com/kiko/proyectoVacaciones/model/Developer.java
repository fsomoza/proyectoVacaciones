package com.kiko.proyectoVacaciones.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="idEmployee")
public class Developer extends Employee {
	
	@ManyToOne
	TeamManager teamManager;
	
	@OneToOne
	@JoinColumn(name="eventId",unique=true)
	Event event;
	
	public Developer(){}
	
	public Developer(String name, String telephone, String email, String image,String password,String role,TeamManager teamManager) {
		super(name, telephone, email, image,password,role);
		this.teamManager = teamManager;
		
	}

	public TeamManager getTeamManager() {
		return teamManager;
	}

	public void setTeamManager(TeamManager teamManager) {
		this.teamManager = teamManager;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package com.kiko.proyectoVacaciones.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="idEmployee")
public class TeamManager extends Employee {
	
	@OneToMany(mappedBy="teamManager")
    List<Developer> developers;
	
	
	public List<Developer> getDevelopers(){
		return this.developers;
		
	}
	
	
	public void setDevelopers(List<Developer> developers) {
	
	this.developers = developers;

}
	
}

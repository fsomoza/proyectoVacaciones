package com.kiko.proyectoVacaciones.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Event {
	
	@OneToOne(mappedBy="event")
	Developer developer;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	
	String text;
	
	LocalDateTime start;
	
	LocalDateTime end;
	
	

	String color;
	
	@PreRemove
	public void nullifyDeveloper() {
		developer.setEvent(null);
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}



	

	public String getColor() { return color; }

	public void setColor(String color) { this.color = color; }
}

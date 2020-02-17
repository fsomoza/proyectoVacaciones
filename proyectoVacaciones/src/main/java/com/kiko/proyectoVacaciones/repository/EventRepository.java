package com.kiko.proyectoVacaciones.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Repository;

import com.kiko.proyectoVacaciones.model.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	
	@Query("SELECT e from Event e where not(e.end < :start or e.start > :end)")
	public List<Event> findBetween(@DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime start, @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end);
	
	@Query("SELECT e from Event e where e.id = :id")
	public Event findComoYoTeDigo(Long id);
	
	@Query("SELECT e from Event e where e.developer.email = :email")
	public List<Event> findEventForDeveloper(String email);
	
	@Query("SELECT e from Event e where e.developer.teamManager.idEmployee = :id")
	public List<Event> findEventsFromMyDevelopers(long id);


}

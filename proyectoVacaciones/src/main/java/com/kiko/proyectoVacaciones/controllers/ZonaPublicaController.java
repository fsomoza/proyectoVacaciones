package com.kiko.proyectoVacaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kiko.proyectoVacaciones.model.Developer;
import com.kiko.proyectoVacaciones.model.Employee;
import com.kiko.proyectoVacaciones.model.Event;
import com.kiko.proyectoVacaciones.model.TeamManager;
import com.kiko.proyectoVacaciones.repository.EventRepository;
import com.kiko.proyectoVacaciones.service.DeveloperService;
import com.kiko.proyectoVacaciones.service.EmployeeService;
import com.kiko.proyectoVacaciones.service.TeamManagerService;



@Controller
@RequestMapping("/public")
public class ZonaPublicaController {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	TeamManagerService teamManagerService;
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	DeveloperService developerService;

	@GetMapping({"/", "/index"})
	public String index(Model model, @RequestParam(name="q", required=false) String query) {
String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Employee employee = employeeService.buscarPorEmail(email);
		
		if (employee.getRole().equals("T")) {
			TeamManager teamManager = teamManagerService.findTeamManager(email);
			model.addAttribute("teamManager", teamManager);	
			return "blank";
		}else {
			Developer developer = developerService.buscarPorEmail(email);
			model.addAttribute("developer", developer);	
			return "blank";
		}
		
	}
	
	@GetMapping("/misSolicitudes")
	public String misSolicitudes(Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Developer developer = developerService.buscarPorEmail(email);
		model.addAttribute("developer", developer);
		return "misSolicitudes";
		
		
	}
	
	
	
	@GetMapping("/solicitudesEmpleados")
	public String solicitudesEmpleados(Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		TeamManager teamManager = teamManagerService.findTeamManager(email);
		long id = teamManager.getIdEmployee();
		 List <Event> events =eventRepository.findEventsFromMyDevelopers(id);
		 Event event = events.get(0);
		model.addAttribute("teamManager", teamManager).addAttribute("event", event);	
		return "solicitudesEmpleados";
		
	}
	

	
	
	@GetMapping("/crearSolicitud")
	public String crearSolicitud(Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Developer developer = developerService.buscarPorEmail(email);
		if (developer.getEvent() == null) {
			model.addAttribute(developer);
			Event event = developer.getEvent();
			return "calendar";
		}else {
			
			Event event = developer.getEvent();
			model.addAttribute(developer).addAttribute(event);
			return "modSolicitud";
		}
		
		
		
	}
	
	

}

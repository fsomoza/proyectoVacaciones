package com.kiko.proyectoVacaciones.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeveloperController {
	
	@GetMapping("/crearSolicDeveloper")
	public String crearSolicitud() {
		
		return "calendar";
		
	}
	

}

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

import com.kiko.proyectoVacaciones.model.Employee;
import com.kiko.proyectoVacaciones.service.EmployeeService;



@Controller
@RequestMapping("/public")
public class ZonaPublicaController {
	@Autowired
	EmployeeService employeeService;
//	@Autowired
//	ProductoServicio productoServicio;
//	
//	
//	@ModelAttribute("productos")
//	public List<Producto> productosNoVendidos() {
//		return productoServicio.productosSinVender();
//	}
//	
	
	@GetMapping({"/", "/index"})
	public String index(Model model, @RequestParam(name="q", required=false) String query) {
String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Employee employee = employeeService.buscarPorEmail(email);
		model.addAttribute("employee", employee);	
		return "blank";
	}
	
	@GetMapping("/producto/{id}")
	public String showProduct(Model model, @PathVariable Long id) {
//		Producto result = productoServicio.findById(id); 
//		if (result != null) {
//			model.addAttribute("producto", result);
//			return "producto";
//		}
		return "redirect:/public";
	}
	
	
	@GetMapping("/calendar")
	public String calendar() {
		return "calendar";
	}
	
	

}

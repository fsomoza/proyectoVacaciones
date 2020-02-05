
package com.kiko.proyectoVacaciones.controllers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kiko.proyectoVacaciones.model.Developer;
import com.kiko.proyectoVacaciones.model.Employee;
import com.kiko.proyectoVacaciones.model.Event;
import com.kiko.proyectoVacaciones.repository.EventRepository;
import com.kiko.proyectoVacaciones.service.DeveloperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
public class CalendarController {

    @Autowired
    EventRepository er;
    @Autowired
	DeveloperService developerService;

    @RequestMapping("/api")
    @ResponseBody
    String home() {
        return "Welcome!";
    }

    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
    	
    	return er.findBetween(start, end);
        
        
    }

    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent(@RequestBody EventCreateParams params) {

        Event e = new Event();
        e.setStart(params.start);
        e.setEnd(params.end);
       e.setText(params.text);
       er.save(e);
       
       String email = SecurityContextHolder.getContext().getAuthentication().getName();
       
       Developer developer = developerService.buscarPorEmail(email);
		developer.setEvent(e);
		developerService.updateDeveloperWithEvent(developer);
       

        return e;
    }

   @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event moveEvent(@RequestBody EventMoveParams params) {

       
       Event e = er.findComoYoTeDigo(params.id);
       System.out.println(e.toString());
       
        e.setStart(params.start);
        e.setEnd(params.end);
        er.save(e);

       

        return e;
    }

   @PostMapping("/api/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event setColor(@RequestBody SetColorParams params) {

        Event e = er.findComoYoTeDigo(params.id);
        e.setColor(params.color);
        er.save(e);

       return e;
   }

   @PostMapping("/api/events/delete")
   @JsonSerialize(using = LocalDateTimeSerializer.class)
   @Transactional
    EventDeleteResponse deleteEvent(@RequestBody EventDeleteParams params) {
        Event e = er.findComoYoTeDigo(params.id);
        er.delete(e);

    return new EventDeleteResponse() {{
           message = "Deleted";
        }};
    }

    public static class EventDeleteParams {
        public Long id;
    }

    public static class EventDeleteResponse {
        public String message;
    }

    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }


}
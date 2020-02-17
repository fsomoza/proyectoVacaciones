package com.kiko.proyectoVacaciones;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.kiko.proyectoVacaciones.model.Developer;
import com.kiko.proyectoVacaciones.model.Event;
import com.kiko.proyectoVacaciones.model.TeamManager;
import com.kiko.proyectoVacaciones.repository.EventRepository;
import com.kiko.proyectoVacaciones.repository.TeamManagerRepository;
import com.kiko.proyectoVacaciones.service.DeveloperService;
import com.kiko.proyectoVacaciones.service.TeamManagerService;
import com.kiko.proyectoVacaciones.upload.StorageService;



@SpringBootApplication
public class ProyectoVacacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoVacacionesApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner initData(DeveloperService service,EventRepository repository,TeamManagerService tmService) {
		return args -> {
			LocalDateTime dt5PorElCuloTeLaHincoMuchisimo;
			LocalDateTime dt1 
            = LocalDateTime 
                  .parse("2018-11-03T12:45:30"); 
			LocalDateTime dt2 
            = LocalDateTime 
                  .parse("2018-11-06T12:45:30"); 
			
			Event event = new Event();
			event.setStart(dt1);
			
			event.setEnd(dt2);
			
			event.setText("hola");
			
			repository.save(event);

			Developer usuario = new Developer("kiko", "666666666", 
					"kiko_somotri@hotmail.es", "https://cdn.sincroguia.tv/uploads/programs/l/a/-/la-salchicha-peleona-202_SPA-73.jpg","admin","D",null);
//			usuario.setEvent(event);
			
			Developer developer2 = new Developer("jayz", "666666666", 
					"jay_z@hotmail.es", "https://s3-us-east-2.amazonaws.com/enterate24backup/wp-content/uploads/2019/11/28205041/jay-z.jpg","admin","D",null);
			
			
			
			

			
				
		     
		
         	TeamManager teamManager = new TeamManager("paco", "666666666", 
				"kiko_somotriko@hotmail.es", "https://cdn.sincroguia.tv/uploads/programs/l/a/-/la-salchicha-peleona-202_SPA-73.jpg","admin","T",null);
         	
         	
         	
         
         	
         	
		     tmService.insertTeamManager(teamManager);
			
			
	       usuario.setTeamManager(teamManager);
			
			developer2.setTeamManager(teamManager);
			
			
			usuario = service.insertarDeveloper(usuario);
			
            developer2 = service.insertarDeveloper(developer2);
			
			List <Event> list;
		
			list = (repository.findBetween(dt1, dt2));
           
			System.out.println(list);

		    event = list.get(0);
		    
		    System.out.println(event.getText());
		    
		    List<Developer> lista = tmService.findTeamManagers(2);
		    
		    
		    Developer developer = lista.get(0);
		    
			System.out.println("este es el desarrollador " + developer.getName());
			
			
			
			
			
			
			
			
			
		    
//		    String email = SecurityContextHolder.getContext().getAuthentication().getName();

		};

}
	
	/**
	 * Este bean se inicia al lanzar la aplicación. Nos permite inicializar el almacenamiento
	 * secundario del proyecto
	 * 
	 * @param storageService Almacenamiento secundario del proyecto
	 * @return
	 */
	@Bean
    CommandLineRunner init(StorageService storageService) {
       return (args) -> {
            storageService.deleteAll();
        storageService.init();
       };
   }	
	
	
}

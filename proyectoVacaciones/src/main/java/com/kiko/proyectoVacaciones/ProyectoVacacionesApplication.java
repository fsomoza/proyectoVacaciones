package com.kiko.proyectoVacaciones;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.kiko.proyectoVacaciones.model.Developer;
import com.kiko.proyectoVacaciones.model.Event;
import com.kiko.proyectoVacaciones.repository.EventRepository;
import com.kiko.proyectoVacaciones.service.DeveloperService;
import com.kiko.proyectoVacaciones.upload.StorageService;



@SpringBootApplication
public class ProyectoVacacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoVacacionesApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner initData(DeveloperService service,EventRepository repository) {
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
					"kiko_somotri@hotmail.es", null,"admin","D",null);
			usuario.setEvent(event);
			
			
			
			usuario = service.insertarDeveloper(usuario);
			
			
			
			
			
			List <Event> list;
			
			 list = (repository.findBetween(dt1, dt2));
           
			System.out.println(list);

		    event = list.get(0);
		    
		    System.out.println(event.getText());
		    
		    
			

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

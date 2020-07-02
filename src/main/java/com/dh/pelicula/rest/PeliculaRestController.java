package com.dh.pelicula.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dh.pelicula.entidades.Pelicula;
import com.dh.pelicula.servicios.PeliculaService;

@RestController
@SuppressWarnings("unused")
public class PeliculaRestController {
	@Autowired
	PeliculaService peliculaService; //usamos Inyeccion de dependencia para Spring core me inyecte el servicio cuando lo use
	
	
	
	@CrossOrigin
	@PostMapping("/guardar")
	public ResponseEntity<Object>  guardarPelicula(@RequestBody Pelicula peli) {

		Pelicula pelicula = new Pelicula();
		pelicula.setCategoria(peli.getCategoria());
		pelicula.setTitulo(peli.getTitulo());
		pelicula.setRating(peli.getRating());
		
		boolean isOk = peliculaService.agregar(pelicula);
		if(true) {
			System.out.print(peli.getTitulo());
			return new ResponseEntity<>(HttpStatus.OK);
		}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		
	}
	
	@CrossOrigin
	@GetMapping("/traerTodas")
	public List<Pelicula>  buscarTodasLasPelicula() {

		return peliculaService.buscarTodasLasPeliculas();
	}
	
}
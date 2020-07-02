package com.dh.pelicula.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dh.pelicula.entidades.Pelicula;
import com.dh.pelicula.repositorios.PeliculaRepository;
import com.dh.pelicula.servicios.PeliculaService;

@Service
public class PeliculaServiceImpl implements PeliculaService{

	@Autowired 
	private PeliculaRepository peliculaRepository;
	@Override
	public boolean agregar(Pelicula peli){
		if (checkTituloAvailable(peli)) {
			generadorNumeroRandom(peli);
			peliculaRepository.save(peli);
		}
		return true;		
	}
	@Override
	public boolean editarPelicula(Pelicula movie) {
		//puedo editar una pelicula asi sin validar?
		if(checkEdicionCorrecta(movie)){
			peliculaRepository.save(movie);
			return true;
		}else {
			return false;
		}
	}
	@Override
	public Pelicula buscarPelicula(Long id)  throws Exception{
		Optional<Pelicula> pelicula= peliculaRepository.findById(id);
		if (pelicula.isPresent()) {
			throw new Exception("pelicula no disponible");
		}
		return pelicula.get();
	}
	@Override
	public Pelicula buscarPeliculaPorTitulo(String titulo) {
		Pelicula peliFound = peliculaRepository.findByTituloIs(titulo);
		return peliFound;
	}
	@Override
	public void eliminarPelicula(long id) {
		//Voy a la BD a buscar la pelicula por id para borrarla
		//Pero me puede pasar que esa pelicula no exista en la BD por eso
		//puedo usar el metodo orElseThrow() para lanzar una exception. 
		//En caso de que tengamos la exception debemos manejarla... se los dejo para que investiguen cómo hacerlo
		//Despues les doy cómo vamos a manejar esa exception pero estaría ideal si investigan por su cuenta que podemos hacer en este caso
		Pelicula peliABorrar = peliculaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalido Id:" + id));
		peliculaRepository.delete(peliABorrar);    
	}

	
	@Override
	public List<Pelicula> buscarTodasLasPeliculas() {
		List<Pelicula> peliculas = (List<Pelicula>) peliculaRepository.findAll();
		return peliculas;
	}


	private boolean checkTituloAvailable(Pelicula peli){
		Pelicula peliFound = peliculaRepository.findByTituloIs(peli.getTitulo()); 
		if (peliFound!=null) {
			return false;
		}
		return true;
	}
	private void generadorNumeroRandom(Pelicula peli) {
		peli.setRandom_num(Math.round(Math.random() * 99999999));
	}
	private boolean checkEdicionCorrecta(Pelicula peliModificada){
		// Otra posible forma de traer el objeto pelicula original de la base de datos
		//Pelicula pelicula = peliculaRepository.findTituloIs(peli1.getTitulo());
		Pelicula peliOriginal = peliculaRepository.findById(peliModificada.getId()).get();
		Long a = peliModificada.getRandom_num();
		Long b = peliOriginal.getRandom_num();
		boolean resultado = (a.equals(b)) ? true: false;
		return resultado;
	}
}

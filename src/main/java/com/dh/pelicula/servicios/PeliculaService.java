package com.dh.pelicula.servicios;

import java.util.List;

import com.dh.pelicula.entidades.Pelicula;

public interface PeliculaService {
	/**
	 * Devuelve una pelicula dado un id determinado
	 * @param id el id de la pelicula a buscar
	 * @return la pelicula segun el id
	 * @throws Exception
	 */
	Pelicula buscarPelicula(Long id) throws Exception;

	/**
	 * retorna una pelicula segun el titulo
	 * @param titulo a buscar
	 * @return Pelicula
	 */
	Pelicula buscarPeliculaPorTitulo(String titulo);

	/**
	 * Permite editar una pelicula
	 * @param movie la pelicula a editar
	 * @return true si se edito con exito
	 */
	boolean editarPelicula(Pelicula movie);

	/**
	 * Genera una nueva pelicula en el sistema
	 * @param movie a guardar
	 * @return true si se guardo ok la pelicula
	 */
	boolean agregar(Pelicula movie);

	void eliminarPelicula(long id);

	List<Pelicula> buscarTodasLasPeliculas();

}

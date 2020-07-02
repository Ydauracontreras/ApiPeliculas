package com.dh.pelicula.repositorios;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dh.pelicula.entidades.Pelicula;



//CRUD refers Create, Read, Update, Delete

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
	List<Pelicula> findByRatingGreaterThan(double rating);
	List<Pelicula> findByRatingLessThan(double rating);
	List<Pelicula> findByRatingLessThanEqual(double rating);
	Pelicula findByTituloIs(String titulo);	
	List<Pelicula> findByCategoriaNotLike(String categoria);
	List<Pelicula> findByTituloStartingWith(String titulo);
	List<Pelicula> findByCategoriaOrderByCategoriaDesc(String categoria);
	
	@Query("select p from Pelicula p where p.rating > ?1")
	List<Pelicula> findRatingGreaterThan(double rating);
	
	@Query("select p from Pelicula p where p.rating < ?1")
	List<Pelicula> findRatingLessThan(double rating);
	
	@Query("select p from Pelicula p where p.rating <= ?1")
	List<Pelicula> findRatingLessThanEqual(double rating);
	
	@Query("select p from Pelicula p where p.titulo = ?1")
	Pelicula findTituloIs(String titulo);
	
	@Query("select p from Pelicula p where p.titulo like %?1%")
	Pelicula findTituloIs2(String titulo);
	
	@Query("select p from Pelicula p where p.categoria not like %?1%")
	List<Pelicula> findCategoriaNotLike(String categoria);
	
	@Query("select p from Pelicula p where p.titulo like ?1%")
	List<Pelicula> findTituloStartingWith(String titulo);
	
	@Query("select p from Pelicula p where p.categoria = ?1 order by p.categoria desc")
	List<Pelicula> findCategoriaOrderByCategoriaDesc(String categoria);
	
	@Query("select p from Pelicula p where p.categoria = ?1")
	List<Pelicula> findCategoriaOrder(String categoria, Sort sort);
}


package com.dh.pelicula.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dh.pelicula.entidades.Pelicula;
import com.dh.pelicula.servicios.PeliculaService;

@Controller // This means that this class is a Controller
public class PeliculaController {
	@Autowired
	private PeliculaService peliculaService;

	@GetMapping("/")
	public String getHome() {
		return "home";
	}

	@GetMapping("/add")
	public String getAgregarPelicula(Model modelo) {
		modelo.addAttribute("movie", new Pelicula());
		return "agregarPelicula";
	}

	@GetMapping("/find")
	public String getBuscar(Model model) {
		model.addAttribute("movie", new Pelicula());
		return "buscarPelicula";
	}

	@GetMapping("/pelicula/get")
	public String getPelicula(Model modeloPelicula, Long id) {
		Pelicula peli = new Pelicula();
		try {
			peli = peliculaService.buscarPelicula(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modeloPelicula.addAttribute("titulo", peli.getTitulo());
		modeloPelicula.addAttribute("categoria", peli.getCategoria());
		modeloPelicula.addAttribute("rating", peli.getRating());
		return "pelicula";
	}

	@PostMapping("/guardoLaPeli") // Map ONLY POST Requests
	public String agregarPelicula(@ModelAttribute Pelicula movie, BindingResult result, Model model) {
		boolean isOk = peliculaService.agregar(movie);
		model.addAttribute("isOk", isOk);
		return "home";
	}

	@PostMapping("/editar") // Map ONLY POST Requests
	public String editarPelicula(@ModelAttribute Pelicula movie, BindingResult result, Model model) {

		try {
			if (peliculaService.editarPelicula(movie)) {
				return "home";
			} else {
				model.addAttribute("prohibido", "Acceso restringido, no tienes los permisos necesarios.");
				return "restringido";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@PostMapping("/find") // Map ONLY POST Requests
	public String buscarPorTitulo(@ModelAttribute Pelicula movie, BindingResult result, Model model) {

		Pelicula peli = peliculaService.buscarPeliculaPorTitulo(movie.getTitulo());
		if (peli != null) {
			model.addAttribute("noEncontrada", false);
			model.addAttribute("movieEncontrada", peli);
			return "pelicula";
		} else {
			model.addAttribute("noEncontrada", true);
			model.addAttribute("movie", new Pelicula());
			return "buscarPelicula";
		}

	}

	/**
	 * Borra una pelicula pasando un id de pelicula
	 * 
	 * @param id    de la pelicula a borrar
	 * @param model
	 * @return la home
	 */
	@GetMapping("/delete/{id}")
	public String borrarPelicula(@PathVariable("id") long id, Model model) {
		// toda la logica, es decir todo el código que necesito para borrar la pelicula
		// lo escribo en
		// el experto de pelicula es decir en peliculaServiceImpl.
		peliculaService.eliminarPelicula(id);
		return "home";
	}

}

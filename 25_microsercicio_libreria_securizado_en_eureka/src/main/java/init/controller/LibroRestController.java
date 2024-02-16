package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.model.Libro;
import init.service.interfaces.LibroServiceInterface;

@RestController
public class LibroRestController {

	@Autowired
	private LibroServiceInterface service;

	// secutiry acceso libre
	@GetMapping(value = "libros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> getAllCursos() {
		return new ResponseEntity<>(service.catalogo(), HttpStatus.OK);
	}
	
	// security acceso libre
	@GetMapping(value = "tematicas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getAllTematicas(){
		return new ResponseEntity<>(service.catalogoTematicas(), HttpStatus.OK);
	}

	// secutiry acceso para usuarios autenticados
	@GetMapping(value = "libro/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> getCursoById(@PathVariable("isbn") int isbn) {
		Libro libro = service.buscarPorIsbn(isbn);
		if (libro != null) {
			return new ResponseEntity<>(libro, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// secutiry acceso para usuarios admin
	@PostMapping(value = "alta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> save(@RequestBody Libro libro) {
		try { // cursoService.agregar(curso), HttpStatus.OK
			service.alta(libro);
		} catch (Exception e) {
			new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import init.model.Libro;
import init.service.interfaces.LibroServiceInterface;

@RestController
public class LibroController {

	@Autowired
	LibroServiceInterface service;

	@GetMapping(value="tematicas",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> catalogo(){
		return new ResponseEntity<>(service.catalogoTematicas(),HttpStatus.OK);
	}
	
	@GetMapping(value="libro/{tematica}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> catalogo(@PathVariable("tematica") String tematica){
		return new ResponseEntity<>(service.buscarLibroPorTematica(tematica), HttpStatus.OK);
	}

}

package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.exceptions.CursoExistenteException;
import init.model.Curso;
import init.service.interfaces.CursoService;

@RestController
//@RequestMapping("/cursos")
public class CursosController {

	@Autowired
	private CursoService cursoService;

	// secutiry acceso libre
	@GetMapping(value = "cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Curso>> getAllCursos() {
		return new ResponseEntity<>(cursoService.buscarTodos(), HttpStatus.OK);
	}

	// secutiry acceso para usuarios autenticados
	@GetMapping(value = "curso/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curso> getCursoById(@PathVariable("id") int id) {
		Curso curso = cursoService.buscarPorId(id);
		if (curso != null) {
			return new ResponseEntity<>(curso, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// secutiry acceso para usuarios autenticados
	@GetMapping(value = "cursos/{min}/{max}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursosRango(@PathVariable("min") double min, @PathVariable("max") double max) {
		return cursoService.buscarCursosPorRangoDePrecio(min, max);
	}

	// secutiry acceso para usuarios admin
	@PostMapping(value = "alta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Curso>> createCurso(@RequestBody Curso curso) {
		try { // cursoService.agregar(curso), HttpStatus.OK
			return new ResponseEntity<>(cursoService.agregar(curso), HttpStatus.OK);
		} catch (CursoExistenteException ex) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	// secutiry acceso para usuarios admin y operators
	@DeleteMapping(value = "eliminar/{denominacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curso> deleteCursoByDenominacion(@PathVariable("denominacion") String denominacion) throws CursoExistenteException {
		return new ResponseEntity<>(cursoService.eliminarCurso(denominacion), HttpStatus.OK);
	}

	// secutiry acceso para usuarios operators
	@PutMapping(value = "/actualizar/{denominacion}")
	public ResponseEntity<Void> updatePreciosByTematica(@PathVariable("denominacion") String denominacion,
			@RequestParam("porcentaje") int porcentaje) {
		cursoService.actualizarPrecio(denominacion, porcentaje);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

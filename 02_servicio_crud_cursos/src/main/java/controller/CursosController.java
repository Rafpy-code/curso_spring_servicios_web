package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import models.Curso;
import service.interfaces.CursoService;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursosController {
	@Autowired
	private CursoService cursoService;

	@GetMapping(value = "buscar", produces = "application/json")
	public List<Curso> getAllCursos() {
		return cursoService.buscarTodos();
	}

	@GetMapping(value = "buscar/{id}", produces = "application/json")
	public Curso getCursoById(@PathVariable int id) {
		Curso curso = cursoService.buscarPorId(id);
		return curso;
	}

	@PostMapping(value = "alta", consumes = "application/json")
	public void createCurso(@RequestBody Curso curso) {
		cursoService.agregar(curso);
	}

	@DeleteMapping(value="eliminar/{denominacion}",produces="application/json")
	public List<Curso> deleteCursoByDenominacion(@PathVariable String denominacion) {
		List<Curso> cursos = cursoService.eliminarResultado(denominacion);
		return cursos;
	}

	@PutMapping(value = "/actualizar-precios/{tematica}", produces = "application/json", consumes = "application/json")
	public List<Curso> updatePreciosByTematica(@PathVariable String tematica, @RequestParam double porcentaje) {
		return cursoService.actualizarPrecio(tematica, porcentaje);
	}
}

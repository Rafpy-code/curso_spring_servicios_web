package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import model.Curso;
import service.interfaces.CursoService;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursosController {
	@Autowired
	private CursoService cursoService;

	@GetMapping(value = "buscar", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> getAllCursos() {
		return cursoService.buscarTodos();
	}

	@GetMapping(value = "buscar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Curso getCursoById(@PathVariable("id") int id) {
		Curso curso = cursoService.buscarPorId(id);
		return curso;
	}

	@PostMapping(value = "alta", produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createCurso(@RequestBody Curso curso) {
		cursoService.agregar(curso);
	}

	@DeleteMapping(value="eliminar/{denominacion}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso deleteCursoByDenominacion(@PathVariable("denominacion") String denominacion) {
		return cursoService.eliminarCurso(denominacion);
	}

	@PutMapping(value = "/actualizar-precios/{denominacion}")
	public void updatePreciosByTematica(@PathVariable("denominacion") String tematica, @RequestParam("porcentaje") int porcentaje) {
		cursoService.actualizarPrecio(tematica, porcentaje);
	}
}

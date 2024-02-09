package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Coche;
import init.service.interfaces.CocheServiceInterface;

@CrossOrigin("*")
@RestController
public class CocheController {

	@Autowired
	CocheServiceInterface service;

	@GetMapping(value = "buscar", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> buscar() {
		return service.findAll();
	}

	@GetMapping(value = "buscarMarca/{marca}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> buscarPorMarca(@PathVariable("marca") String marca) {
		return service.findByMarca(marca);
	}
	
	@GetMapping(value = "buscarPrecioMax/{precio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> buscarPorPrecioMax(@PathVariable("precio") double precio) {
		return service.findByPrecioMax(precio);
	}

	@PostMapping(value = "alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alta(@RequestBody Coche coche) {
		service.save(coche);
	}

	@DeleteMapping(value = "eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public Coche eliminar(@RequestParam("matricula") String matricula) {
		return service.delete(matricula);
	}

	@PutMapping(value = "actualizar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@RequestBody Coche coche) {
		service.update(coche);
	}

}

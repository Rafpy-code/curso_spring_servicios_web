package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import init.model.Vehiculo;
import init.service.interfaces.VehiculoServiceInterface;

@CrossOrigin("*")
@RestController
public class VehiculoController {
	
	@Autowired
	VehiculoServiceInterface service;
	
	@GetMapping(value = "vehiculos/{km1}/{km2}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vehiculo> buscarPorPrecioMax(@PathVariable("km1") int km1, @PathVariable("km2") int km2 ) {
		return service.findByRangoKm(km1, km2);
	}
	
	@GetMapping(value = "vehiculos/{precio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vehiculo> buscarPorPrecioMax(@PathVariable("precio") double precio) {
		return service.findByPrecioMax(precio);
	}

}

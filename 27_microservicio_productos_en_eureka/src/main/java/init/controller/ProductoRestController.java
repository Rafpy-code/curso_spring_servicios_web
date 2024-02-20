package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Producto;
import init.service.interfaces.ProductoServiceInterface;

@CrossOrigin("*")
@RestController
public class ProductoRestController {

	@Autowired
	ProductoServiceInterface service;

	@GetMapping(value = "productos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> getAllProductos() {
		return new ResponseEntity<>(service.getProductos(), HttpStatus.OK);
	}

	@PutMapping(value = "actualizar/{codigoProducto}/{unidadesCompradas}")
	public ResponseEntity<Void> updateStock(@PathVariable("codigoProducto") int codigoProducto,
			@PathVariable("unidadesCompradas") int unidadesCompradas) {
		try {
			service.putProducto(codigoProducto, unidadesCompradas);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@GetMapping(value = "precio/{codigoProducto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> getCursoById(@PathVariable("codigoProducto") int codigoProducto) {
		try {
			double precioUnitario = service.getPrecio(codigoProducto);
			return new ResponseEntity<>(precioUnitario, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}

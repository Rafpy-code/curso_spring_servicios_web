package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Pedido;
import init.service.interfaces.PedidoServiceInterface;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RestController
public class PedidoRestController {

	@Autowired
	PedidoServiceInterface service;

	@GetMapping(value = "pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pedido>> getAllProductos() {
		return new ResponseEntity<>(service.getPedidos(), HttpStatus.OK);
	}

	@PostMapping(value = "alta", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createCurso(@RequestBody Pedido pedido) {
		try {
			service.saveProducto(pedido);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

}

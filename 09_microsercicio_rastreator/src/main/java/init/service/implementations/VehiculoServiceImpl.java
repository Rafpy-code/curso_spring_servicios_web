package init.service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.model.Vehiculo;
import init.service.interfaces.VehiculoServiceInterface;

@Service
public class VehiculoServiceImpl implements VehiculoServiceInterface {

	@Autowired
	RestClient restClient;

	String url = "http://localhost:8008/";
	
	@Override
	public List<Vehiculo> findByRangoKm(int km1, int km2) {
		return Arrays.asList(restClient.get().uri(url+"buscar").retrieve().body(Vehiculo[].class))
				.stream()
				.peek(v -> v.setTipo("coche"))
				.filter(v -> v.getKilometros() <= km2 && v.getKilometros()>= km1)
				.toList();
	}

	@Override
	public List<Vehiculo> findByPrecioMax(double precioMax) {
		return Arrays.asList(restClient.get().uri(url+"buscarPrecioMax/"+precioMax).retrieve().body(Vehiculo[].class))
				.stream()
				.peek(v -> v.setTipo("coche"))
				.filter(v -> v.getPrecio() <= precioMax).toList();
	}

}

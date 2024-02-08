package service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import model.Formacion;
import service.interfaces.IFormacionServices;

@Service
public class FormacionServiceImpl implements IFormacionServices {

	@Autowired
	RestClient restClient;
	
	String urlBase="http://localhost:8080/02_servicio_crud_cursos/";

	@Override
	public List<Formacion> catalogo() {
		return Arrays.asList(restClient.get().uri(urlBase+"buscar").retrieve().body(Formacion[].class));
	}

	@Override
	public List<Formacion> catalogoPorDuracionMax(int max) {
		return catalogo()
				.stream()
				.filter((f->f.getHoras() <= max))
				.toList();
						
	}

	@Override
	public void alta(Formacion formacion) {
		restClient.post().uri(urlBase+"alta").contentType(MediaType.APPLICATION_JSON).body(formacion).retrieve();

	}

}

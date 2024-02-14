package init.service.implementations;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.model.Libro;
import init.service.interfaces.LibroServiceInterface;

@Service
public class LibroServiceImpl implements LibroServiceInterface {

	@Autowired
	RestClient restClient;
	
	@Value("${app.user}")
	String usuario;
	@Value("${app.password}")
	String password;
	String urlBase="http://localhost:8080/";
	
	private List<Libro> catalogoLibros(){
		return Arrays.asList(restClient.get()
				.uri(urlBase+"libros")
				.retrieve()
				.body(Libro[].class) //Libro[].class
				);
	}

	@Override
	public List<String> catalogoTematicas() {
		return Arrays.asList(restClient.get()
				.uri(urlBase+"tematicas")
				.retrieve()
				.body(String[].class) //String[].class
				);
	}

	@Override
	public List<Libro> buscarLibroPorTematica(String tematica) {
		return catalogoLibros()
				.stream()
				.filter(l -> l.getTematica().equals(tematica))
				.toList();
	}
	
	private String getBase64(String us, String pwd) {
		String cad=us+":"+pwd;
		return Base64.getEncoder().encodeToString(cad.getBytes());
	}

}

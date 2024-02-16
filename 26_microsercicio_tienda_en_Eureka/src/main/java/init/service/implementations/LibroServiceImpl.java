package init.service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import init.model.Libro;
import init.model.TokenResponse;
import init.service.interfaces.LibroServiceInterface;
import jakarta.annotation.PostConstruct;

@Service
public class LibroServiceImpl implements LibroServiceInterface {

	@Autowired
	@Qualifier("ribbonClient")
	RestClient restClientRibbon;
	@Autowired
	@Qualifier("noRibbonClient")
	RestClient restClientNoRibbon;
	
	
	@Value("${app.urlAuth}")
	String urlAuth;
	@Value("${app.client_id}")
	String clientId;
	@Value("${app.username}")
	String username;
	@Value("${app.password}")
	String password;
	@Value("${app.grant_type}")
	String grantType;
	
	String urlBase="http://servicio-libreria/";
	String token;
	
	@PostConstruct
	public void init() {
		token = getToken();
	}
	
	private List<Libro> catalogoLibros(){
		return Arrays.asList(
				restClientRibbon.get()
				.uri(urlBase+"libros")
				.retrieve()
				.body(Libro[].class) //Libro[].class
				);
	}

	@Override
	public List<String> catalogoTematicas() {
		return Arrays.asList(
				restClientRibbon.get()
				.uri(urlBase+"tematicas")
				.header("Authorization", "Bearer "+getToken())
				.retrieve()
				.body(String[].class) //String[].class
				);
	}

	@Override
	public List<Libro> buscarLibroPorTematica(String tematica) {
		return Arrays.asList(
				restClientRibbon.get()
				.uri(urlBase+"libros")
				.header("Authorization", "Bearer "+getToken())
				.retrieve()
				.body(Libro[].class)
			)
			.stream()
			.filter(l -> l.getTematica().equals(tematica))
			.toList();
	}
	
	private String getToken() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("client_id", clientId);
		params.add("username", username);
		params.add("password", password);
		params.add("grant_type", grantType);
		//System.out.println(username + " - " + password);
		return restClientNoRibbon.post()
		.uri(urlAuth)
		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		.body(params)
		.retrieve()
		.body(TokenResponse.class) // devuelve un objeto TokenResponse
		.getAccess_token();
	}

}

package init.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import init.model.Resultado;

@Component
public class TestBuscador implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		
		String url = "http://localhost:8080/buscar";
		WebClient client = WebClient.create();
		client.get()
			.uri(url + "?tematica=libros")
			.retrieve()
			.bodyToFlux(Resultado.class) //Flux<Resultado>
			.doOnComplete(() -> System.out.println("No hay más resultados"))
			// nos subscribimos al flujo
			.subscribe(r -> System.out.println(r.getUrl()));

	}

}

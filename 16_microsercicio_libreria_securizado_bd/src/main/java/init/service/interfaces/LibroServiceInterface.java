package init.service.interfaces;

import java.util.List;
import init.model.Libro;

public interface LibroServiceInterface {

	List<Libro> catalogo();
	List<Libro> catalogoTematicas();
	Libro buscarPorIsbn(int isbn);		// solo usuarios autenticados
	void alta(Libro libro);				// solo administradores

	
}

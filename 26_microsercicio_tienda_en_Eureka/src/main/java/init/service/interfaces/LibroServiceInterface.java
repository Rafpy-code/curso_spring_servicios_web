package init.service.interfaces;

import java.util.List;

import init.model.Libro;

public interface LibroServiceInterface {
	
	List<String> catalogoTematicas();
	List<Libro> buscarLibroPorTematica(String tematica);

}

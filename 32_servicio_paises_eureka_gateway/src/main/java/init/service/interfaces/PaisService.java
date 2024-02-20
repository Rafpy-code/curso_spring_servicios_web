package init.service.interfaces;

import java.util.List;

import init.model.Pais;

public interface PaisService {
	
	List<String> continentes();
	
	List<Pais> paisesPorContinente(String continente);
	
	Pais paisMasPoblado();
	

}

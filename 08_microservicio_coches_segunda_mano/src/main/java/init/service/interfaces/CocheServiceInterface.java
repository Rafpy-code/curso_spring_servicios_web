package init.service.interfaces;

import java.util.Date;
import java.util.List;

import init.model.Coche;

public interface CocheServiceInterface {
	
	void save(Coche coche);
	List<Coche> findAll();
	Coche delete(String matricula);
	List<Coche> findByMarca(String marca);
	List<Coche> findByPrecioMax(double precioMax);
	void update(Coche coche);
	
}

/*
Funcionalidades:
-alta de coches (no matriculas repetidas)
-catalogo de todos los coches
-eliminar coche por matricula y devuelve el coche por eliminado
-consulta de coches por marca
-consulta de coches por precio maximo
-actualizacion de coches a partir de la matricula recibida
 */
package init.service.interfaces;

import java.util.List;

import init.model.Vehiculo;

public interface VehiculoServiceInterface {
	
	List<Vehiculo> findByRangoKm(int km1, int km2);
	List<Vehiculo> findByPrecioMax(double precioMax);

}

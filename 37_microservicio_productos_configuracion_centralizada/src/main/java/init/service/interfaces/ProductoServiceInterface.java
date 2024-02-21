package init.service.interfaces;

import java.util.List;

import init.model.Producto;

public interface ProductoServiceInterface {
	
	List<Producto> getProductos();
	void putProducto(int codigoProducto, int unidadesCompradas);
	double getPrecio (int codigoProducto);

}

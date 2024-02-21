package init.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.ProductoDao;
import init.model.Producto;
import init.service.interfaces.ProductoServiceInterface;

@Service
public class ProductoServiceImpl implements ProductoServiceInterface {

	@Autowired
	ProductoDao productoDao;

	private Producto getProducto(int codigoProducto) {
		return productoDao.findById(codigoProducto).orElse(null);
	}

	@Override
	public List<Producto> getProductos() {
		return productoDao.findAll();
	}

	@Override
	public void putProducto(int codigoProducto, int unidadesCompradas) {
		Producto p = getProducto(codigoProducto);
		if (p != null && p.getStock() >= unidadesCompradas) {
			productoDao.updatePrecio(codigoProducto, unidadesCompradas);
		} else {
			System.out.println("El Stock disponible es de: " + p.getStock() + " unidades.");
		}
	}

	@Override
	public double getPrecio(int codigoProducto) {
		Producto p = getProducto(codigoProducto);
		if (p != null) {
			return p.getPrecioUnitario();
		}
		System.out.println("No existe ese producto");
		return 0;
	}

}

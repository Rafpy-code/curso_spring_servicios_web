package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import init.model.Producto;
import jakarta.transaction.Transactional;

public interface ProductoDao extends JpaRepository<Producto, Integer> {

	@Transactional
	@Modifying
	@Query("update Producto p set p.stock = (p.stock - ?2) where p.codigoProducto=?1")
	void updatePrecio(int codigoProducto, int unidades);

}

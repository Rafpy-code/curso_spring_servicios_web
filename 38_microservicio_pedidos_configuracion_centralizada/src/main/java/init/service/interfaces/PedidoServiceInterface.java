package init.service.interfaces;

import java.util.List;

import init.model.Pedido;

public interface PedidoServiceInterface {
	
	List<Pedido> getPedidos();
	void saveProducto(Pedido pedido);

}

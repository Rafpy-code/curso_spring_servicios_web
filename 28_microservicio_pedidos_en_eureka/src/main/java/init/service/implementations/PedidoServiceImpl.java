package init.service.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClient;

import init.dao.PedidoDao;
import init.model.Pedido;
import init.service.interfaces.PedidoServiceInterface;

@Service
public class PedidoServiceImpl implements PedidoServiceInterface {

	@Autowired
	PedidoDao pedidoDao;
	@Autowired
	RestClient restClient;

	String urlBase = "http://servicio-productos/";

	private Pedido getPedido(int idPedido) {
		return pedidoDao.findById(idPedido).orElse(null);
	}

	@Override
	public List<Pedido> getPedidos() {
		return pedidoDao.findAll();
	}

	@Override
	public void saveProducto(@RequestBody Pedido pedido) {
		String urlBase = "http://servicio-productos/";
		pedido.setFechaPedido(new Date());
		// obtenemos precio producto llamado al recurso remoto
		double precio = Double.parseDouble(
				restClient.get()
				.uri(urlBase + "precio/" + pedido.getCodigoProducto())
				.retrieve()
				.body(String.class));
		pedido.setTotal(pedido.getUnidades() * precio);
		// salvamos pedido
		pedidoDao.save(pedido);
		// actualizamos stock llamado a recurso remoto
		restClient
		.put()
		.uri(urlBase + "actualizar/" + pedido.getCodigoProducto() + "/" + pedido.getUnidades())
		.retrieve();

	}

}

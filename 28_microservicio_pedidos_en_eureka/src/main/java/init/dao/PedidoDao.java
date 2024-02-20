package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Pedido;

public interface PedidoDao extends JpaRepository<Pedido, Integer> {

}

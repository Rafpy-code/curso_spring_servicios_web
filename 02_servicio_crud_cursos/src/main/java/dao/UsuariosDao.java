package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import models.entity.Usuario;

public interface UsuariosDao extends JpaRepository<Usuario, String> {
	
	Usuario findByUsuarioAndPassword(String u, String p);
	Usuario findByUsuario(String usuario);

}

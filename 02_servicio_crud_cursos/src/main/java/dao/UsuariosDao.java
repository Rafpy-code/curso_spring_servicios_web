package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Usuario;

public interface UsuariosDao extends JpaRepository<Usuario, String> {
	
	Usuario findByUsuarioAndPassword(String u, String p);
	Usuario findByUsuario(String usuario);

}

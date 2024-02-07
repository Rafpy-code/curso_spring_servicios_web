package service.interfaces;

import model.Usuario;

public interface UsuarioService {
	Usuario autenticarUsuario(String usuario, String password);
	boolean altaUsuario(Usuario usuario);
}

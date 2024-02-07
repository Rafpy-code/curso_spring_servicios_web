package service.interfaces;

import models.entity.Usuario;

public interface UsuarioService {
	Usuario autenticarUsuario(String usuario, String password);
	boolean altaUsuario(Usuario usuario);
}

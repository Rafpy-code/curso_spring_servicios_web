package service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UsuariosDao;
import models.entity.Usuario;
import service.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {	

	@Autowired
	private UsuariosDao usuariosDao;

	@Override
	public Usuario autenticarUsuario(String usuario, String password) {
		Usuario usu = usuariosDao.findByUsuarioAndPassword(usuario, password);
		return usu != null ? usu : null;
	}

	@Override
	public boolean altaUsuario(Usuario usuario) {
		if(usuariosDao.findByUsuario(usuario.getUsuario())==null) {
			usuariosDao.save(usuario);
			return true;
		}
		return false;
	}

}

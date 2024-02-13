package init.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.LibroDao;
import init.model.Libro;
import init.service.interfaces.LibroServiceInterface;

@Service
public class LibroServiceImpl implements LibroServiceInterface {
	
	@Autowired
	private LibroDao libroDao;

	@Override
	public List<Libro> catalogo() {
		return libroDao.findAll();
	}

	@Override
	public List<Libro> catalogoTematicas() {
		return libroDao.findAll().stream().distinct().peek(l -> l.getTematica()).toList();
	}

	@Override
	public Libro buscarPorIsbn(int isbn) {
		return libroDao.findById(isbn).orElse(null);
	}

	@Override
	public void alta(Libro libro) {
		if(buscarPorIsbn(libro.getIsbn()) != null) {
			libroDao.save(libro);
		}
	}

}

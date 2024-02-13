package init.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.CursosDao;
import init.exceptions.CursoExistenteException;
import init.service.interfaces.CursoService;
import init.model.Curso;

@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursosDao cursosDao;
	
	@Override
	public List<Curso> buscarTodos() {
		return cursosDao.findAll();
	}

	@Override
	public Curso buscarPorId(int idCurso) {
		return cursosDao.findById(idCurso).orElse(null);
	}

	@Override
	public List<Curso> buscarCursosPorRangoDePrecio(double precioMin, double precioMax) {
		return cursosDao.findByPrecioBetween(precioMin, precioMax);
	}

	@Override
	public List<Curso> agregar(Curso curso) throws CursoExistenteException {
		if(cursosDao.findByDenominacion(curso.getDenominacion())!=null) {
			// Si ya hay un curso con ese nombre provocamos una excepción
			throw new CursoExistenteException();
		}
		cursosDao.save(curso);
		return buscarTodos();
		
	}

	@Override
	public Curso eliminarCurso(String denominacion) {
		Curso curso = cursosDao.findByDenominacion(denominacion);
		if(curso != null) {
			cursosDao.deleteByDenominacion(denominacion);
		}
		return curso;
	}

	@Override
	public void actualizarPrecio(String denominacion, int porcentaje) {
		cursosDao.updatePrecio(denominacion, porcentaje);
	}

}

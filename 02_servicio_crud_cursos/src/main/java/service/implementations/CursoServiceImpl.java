package service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CursosDao;
import model.Curso;
import service.interfaces.CursoService;

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
	public void agregar(Curso curso) {
		cursosDao.save(curso);
		
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

package service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CursosDao;
import models.entity.Curso;
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
	public List<Curso> eliminarResultado(String denominacion) {
		List<Curso> cursos = cursosDao.findAll();
		cursos.removeIf(c -> c.getDenominacion().equals(denominacion));
		return cursos;
	}

	@Override
	public List<Curso> actualizarPrecio(String denominacion, double nuevoPrecio) {
		List<Curso> cursos = cursosDao.findAll();
		List<Curso> cursosEncontrados = cursos.stream()
			.filter(c-> c.getDenominacion().equals(denominacion))
			.toList();
		for(Curso curso : cursosEncontrados) {
			curso.setPrecio(nuevoPrecio);
		}
		
		return cursosEncontrados;
	}

}

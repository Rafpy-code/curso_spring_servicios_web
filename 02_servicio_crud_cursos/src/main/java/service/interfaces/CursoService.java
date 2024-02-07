package service.interfaces;

import java.util.List;

import models.Curso;

public interface CursoService {
	List<Curso> buscarTodos();
	Curso buscarPorId(int idCurso);
	List<Curso> buscarCursosPorRangoDePrecio(double precioMin, double precioMax);
	void agregar(Curso curso);
	List<Curso> eliminarResultado(String denominacion);
	List<Curso> actualizarPrecio(String denominacio, double nuevoPrecio);
}

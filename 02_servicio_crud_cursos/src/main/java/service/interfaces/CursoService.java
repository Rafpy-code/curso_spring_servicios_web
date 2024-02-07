package service.interfaces;

import java.util.List;

import model.Curso;

public interface CursoService {
	List<Curso> buscarTodos();
	Curso buscarPorId(int idCurso);
	List<Curso> buscarCursosPorRangoDePrecio(double precioMin, double precioMax);
	void agregar(Curso curso);
	Curso eliminarCurso(String denominacion);
	void actualizarPrecio(String denominacio, int porcentaje);
}

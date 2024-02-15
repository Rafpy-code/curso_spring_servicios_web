package init.service.interfaces;

import java.util.List;

import init.exceptions.CursoExistenteException;
import init.model.Curso;

public interface CursoService {
	List<Curso> buscarTodos();
	Curso buscarPorId(int idCurso);
	List<Curso> buscarCursosPorRangoDePrecio(double precioMin, double precioMax);
	List<Curso> agregar(Curso curso) throws CursoExistenteException;
	Curso eliminarCurso(String denominacion);
	void actualizarPrecio(String denominacio, int porcentaje);
}

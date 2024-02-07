package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import model.Curso;

public interface CursosDao extends JpaRepository<Curso, Integer> {
	
	// @Query("select c from Curso c where c.idCurso=?1")
	// Curso findById(int idCurso); //Heredado
	
	// Ya está implicita la query
	List<Curso> findByPrecioBetween(double precioMin, double precioMax);
	
	// @Query("select c from Curso c where c.denominacion=?1")
	Curso findByDenominacion(String denominacion);
	
	@Transactional
	@Modifying
	//@Query("delete Curso c where c.denominacion=?1")
	void deleteByDenominacion(String denominacion);
	
	@Transactional
	@Modifying
	@Query("update Curso c set c.precio = c.precio*(100+?2)/100 where c.denominacion=?1")
	void updatePrecio(String denominacion, int porcentaje);
	
}

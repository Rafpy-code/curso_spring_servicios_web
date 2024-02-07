package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import models.entity.Curso;

public interface CursosDao extends JpaRepository<Curso, Integer> {
	
	List<Curso> findByPrecioBetween(double precioMin, double precioMax);
}

package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Libro;

public interface LibroDao extends JpaRepository<Libro, Integer> {

	
	
}

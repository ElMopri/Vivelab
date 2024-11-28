package co.edu.ufps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
	public List<Instructor> findAllByNombreContainingIgnoreCase(String nombre);
}

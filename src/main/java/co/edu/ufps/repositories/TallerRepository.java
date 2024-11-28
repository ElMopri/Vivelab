package co.edu.ufps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Taller;

public interface TallerRepository extends JpaRepository<Taller, Integer>{
	public List<Taller> findAllByNombreContainingIgnoreCase(String nombre);
}

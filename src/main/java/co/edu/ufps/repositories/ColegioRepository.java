package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Colegio;
import co.edu.ufps.entities.Municipio;

public interface ColegioRepository extends JpaRepository<Colegio, Integer> {
	public boolean existsByMunicipio(Municipio municipio);
}

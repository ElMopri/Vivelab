package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Colegio;

public interface ColegioRepository extends JpaRepository<Colegio, Integer> {

}

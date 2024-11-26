package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Sesion;

public interface SesionRepository extends JpaRepository<Sesion, Integer> {

}

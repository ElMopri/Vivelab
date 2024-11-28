package co.edu.ufps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Evidencia;
import co.edu.ufps.entities.Sesion;

public interface EvidenciaRepository extends JpaRepository<Evidencia, Integer>{
	public List<Evidencia> findAllBySesion(Sesion sesion);
	public boolean existsBySesion(Sesion sesion);
}

package co.edu.ufps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Instructor;
import co.edu.ufps.entities.Programacion;
import co.edu.ufps.entities.Sesion;
import co.edu.ufps.entities.Ubicacion;

public interface SesionRepository extends JpaRepository<Sesion, Integer> {
	public boolean existsByProgramacion(Programacion programacion);
	public boolean existsByInstructor(Instructor instructor);
	public boolean existsByUbicacion(Ubicacion ubicacion);
	public List<Sesion> findAllByProgramacion_Taller_NombreContainingIgnoreCase(String nombre);
}

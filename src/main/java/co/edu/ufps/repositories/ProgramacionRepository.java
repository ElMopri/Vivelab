package co.edu.ufps.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Colegio;
import co.edu.ufps.entities.Instructor;
import co.edu.ufps.entities.Programacion;
import co.edu.ufps.entities.Taller;
import co.edu.ufps.entities.Ubicacion;

public interface ProgramacionRepository extends JpaRepository<Programacion, Integer>{
	public boolean existsByTaller(Taller taller);
	public boolean existsByColegio(Colegio colegio);
	public boolean existsByInstructor(Instructor instructor);
	public boolean existsByUbicacion(Ubicacion ubicacion);
	public List<Programacion> findAllByTaller_NombreContainingIgnoreCaseOrFechaInicioOrFechaFin(String nombre, LocalDate fechaInicio, LocalDate fechaFin);

}

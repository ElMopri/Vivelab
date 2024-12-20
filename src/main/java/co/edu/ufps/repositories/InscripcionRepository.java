package co.edu.ufps.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Inscripcion;
import co.edu.ufps.entities.Participante;
import co.edu.ufps.entities.Programacion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer>{
	public boolean existsByProgramacion(Programacion programacion);
	public boolean existsByParticipante(Participante participante);
	public boolean existsByParticipanteAndProgramacion(Participante participante, Programacion programacion);
	public List<Inscripcion> findByProgramacion(Programacion programacion);
	public List<Inscripcion> findAllByParticipante_NombreContainingIgnoreCase(String nombre);
	public List<Inscripcion> findAllByFecha(LocalDate fecha);
}

package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Inscripcion;
import co.edu.ufps.entities.Participante;
import co.edu.ufps.entities.Programacion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer>{
	public boolean existsByProgramacion(Programacion programacion);
	public boolean existsByParticipante(Participante participante);
}

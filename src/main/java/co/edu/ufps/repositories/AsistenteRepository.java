package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Asistente;
import co.edu.ufps.entities.Participante;
import co.edu.ufps.entities.Sesion;

public interface AsistenteRepository extends JpaRepository<Asistente, Integer> {
	public Asistente findBySesionAndParticipante(Sesion sesion, Participante participante);
	public boolean existsBySesionAndParticipante(Sesion sesion, Participante participante);
	public boolean existsBySesion(Sesion sesion);
	public boolean existsByParticipante(Participante participante);
}

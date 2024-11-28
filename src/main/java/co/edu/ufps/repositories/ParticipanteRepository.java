package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.entities.Colegio;
import co.edu.ufps.entities.Participante;
import co.edu.ufps.entities.TipoDocumento;

public interface ParticipanteRepository extends JpaRepository<Participante, Integer>{
	public boolean existsByTipoDocumento(TipoDocumento tipoDocumento);
	public boolean existsByColegio(Colegio colegio);
}

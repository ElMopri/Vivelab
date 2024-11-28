package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Participante;
import co.edu.ufps.repositories.AsistenteRepository;
import co.edu.ufps.repositories.InscripcionRepository;
import co.edu.ufps.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {
	@Autowired
	private ParticipanteRepository participanteRepository;
	
	@Autowired
	private AsistenteRepository asistenteRepository;
	
	@Autowired
	private InscripcionRepository inscripcionRepository;

	public List<Participante> list() {
		return participanteRepository.findAll();
	}
	
	public Participante get(Integer id) {
		Optional<Participante> participanteOpt = participanteRepository.findById(id);
		if (participanteOpt.isPresent()) {
			return participanteOpt.get();
		}
		return null;
	}

	public Participante create(Participante participante) {
		return participanteRepository.save(participante);
	}

	public Participante update(Integer id, Participante participante) {
		Optional<Participante> participanteOpt = participanteRepository.findById(id);
		if (!participanteOpt.isPresent()) {
			return null;
		}
		Participante updatedParticipante = participanteOpt.get();
		updatedParticipante.setNombre(participante.getNombre());
		updatedParticipante.setColegio(participante.getColegio());
		updatedParticipante.setTipoDocumento(participante.getTipoDocumento());
		return participanteRepository.save(updatedParticipante);
	}

	public Participante delete(Integer id) {
	    Optional<Participante> participanteOpt = participanteRepository.findById(id);
	    if (!participanteOpt.isPresent()) {
	        throw new IllegalArgumentException("El participante con id " + id + " no existe.");
	    }
	    Participante participante = participanteOpt.get();
	    if (asistenteRepository.existsByParticipante(participante)) {
	        throw new IllegalStateException("No se puede eliminar el participante porque tiene asistentes relacionados.");
	    }
	    if (inscripcionRepository.existsByParticipante(participante)) {
	        throw new IllegalStateException("No se puede eliminar el participante porque tiene inscripciones relacionadas.");
	    }
	    participanteRepository.delete(participante);
	    return participante;
	}
}

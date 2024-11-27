package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Participante;
import co.edu.ufps.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {
	@Autowired
	private ParticipanteRepository participanteRepository;

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
		updatedParticipante.setColegio_id(participante.getColegio_id());
		updatedParticipante.setTipo_documento_id(participante.getTipo_documento_id());
		return participanteRepository.save(updatedParticipante);
	}

	public Participante delete(Integer id) {
		Optional<Participante> participanteOpt = participanteRepository.findById(id);
		if (participanteOpt.isPresent()) {
			Participante participante = participanteOpt.get();
			participanteRepository.delete(participante);
			return participante;
		}
		return null;
	}
}

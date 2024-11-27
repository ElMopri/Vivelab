package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Asistente;
import co.edu.ufps.repositories.AsistenteRepository;

@Service
public class AsistenteService {
	@Autowired
	private AsistenteRepository asistenteRepository;

	public List<Asistente> list() {
		return asistenteRepository.findAll();
	}
	
	public Asistente get(Integer id) {
		Optional<Asistente> asistenteOpt = asistenteRepository.findById(id);
		if (asistenteOpt.isPresent()) {
			return asistenteOpt.get();
		}
		return null;
	}

	public Asistente create(Asistente asistente) {
		return asistenteRepository.save(asistente);
	}

	public Asistente update(Integer id, Asistente asistente) {
		Optional<Asistente> asistenteOpt = asistenteRepository.findById(id);
		if (!asistenteOpt.isPresent()) {
			return null;
		}
		Asistente updatedAsistente = asistenteOpt.get();
		updatedAsistente.setSesion(asistente.getSesion());
		updatedAsistente.setParticipante(asistente.getParticipante());
		return asistenteRepository.save(updatedAsistente);
	}

	public Asistente delete(Integer id) {
		Optional<Asistente> asistenteOpt = asistenteRepository.findById(id);
		if (asistenteOpt.isPresent()) {
			Asistente asistente = asistenteOpt.get();
			asistenteRepository.delete(asistente);
			return asistente;
		}
		return null;
	}
}

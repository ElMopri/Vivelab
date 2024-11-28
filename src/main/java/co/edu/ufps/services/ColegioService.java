package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Colegio;
import co.edu.ufps.repositories.ColegioRepository;
import co.edu.ufps.repositories.ParticipanteRepository;
import co.edu.ufps.repositories.ProgramacionRepository;

@Service
public class ColegioService {
	@Autowired
	private ColegioRepository colegioRepository;
	
	@Autowired
	private ParticipanteRepository participanteRepository;
	
	@Autowired
	private ProgramacionRepository programacionRepository;

	public List<Colegio> list() {
		return colegioRepository.findAll();
	}
	
	public Colegio get(Integer id) {
		Optional<Colegio> colegioOpt = colegioRepository.findById(id);
		if (colegioOpt.isPresent()) {
			return colegioOpt.get();
		}
		return null;
	}

	public Colegio create(Colegio colegio) {
		return colegioRepository.save(colegio);
	}

	public Colegio update(Integer id, Colegio colegio) {
		Optional<Colegio> colegioOpt = colegioRepository.findById(id);
		if (!colegioOpt.isPresent()) {
			return null;
		}
		Colegio updatedColegio = colegioOpt.get();
		updatedColegio.setNombre(colegio.getNombre());
		updatedColegio.setMunicipio(colegio.getMunicipio());
		updatedColegio.setDane(colegio.getDane());
		return colegioRepository.save(updatedColegio);
	}
	
	public Colegio delete(Integer id) {
	    Optional<Colegio> colegioOpt = colegioRepository.findById(id);
	    if (!colegioOpt.isPresent()) {
	        throw new IllegalArgumentException("El colegio con id " + id + " no existe.");
	    }
	    Colegio colegio = colegioOpt.get();
	    if (participanteRepository.existsByColegio(colegio)) {
	        throw new IllegalStateException("No se puede eliminar el colegio porque tiene participantes relacionados.");
	    }
	    if (programacionRepository.existsByColegio(colegio)) {
	        throw new IllegalStateException("No se puede eliminar el colegio porque tiene programaciones relacionadas.");
	    }
	    colegioRepository.delete(colegio);
	    return colegio;
	}

}
package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Taller;
import co.edu.ufps.repositories.TallerRepository;

@Service
public class TallerService {
	@Autowired
	private TallerRepository tallerRepository;

	public List<Taller> list() {
		return tallerRepository.findAll();
	}
	
	public Taller get(Integer id) {
		Optional<Taller> tallerOpt = tallerRepository.findById(id);
		if (tallerOpt.isPresent()) {
			return tallerOpt.get();
		}
		return null;
	}

	public Taller create(Taller taller) {
		return tallerRepository.save(taller);
	}

	public Taller update(Integer id, Taller taller) {
		Optional<Taller> tallerOpt = tallerRepository.findById(id);
		if (!tallerOpt.isPresent()) {
			return null;
		}
		Taller updatedTaller = tallerOpt.get();
		updatedTaller.setNombre(taller.getNombre());
		updatedTaller.setDescripcion(taller.getDescripcion());
		return tallerRepository.save(updatedTaller);
	}

	public Taller delete(Integer id) {
		Optional<Taller> tallerOpt = tallerRepository.findById(id);
		if (tallerOpt.isPresent()) {
			Taller taller = tallerOpt.get();
			tallerRepository.delete(taller);
			return taller;
		}
		return null;
	}
}

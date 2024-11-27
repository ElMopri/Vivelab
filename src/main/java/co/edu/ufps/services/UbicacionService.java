package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Ubicacion;
import co.edu.ufps.repositories.UbicacionRepository;

@Service
public class UbicacionService {
	@Autowired
	private UbicacionRepository ubicacionRepository;

	public List<Ubicacion> list() {
		return ubicacionRepository.findAll();
	}
	
	public Ubicacion get(Integer id) {
		Optional<Ubicacion> ubicacionOpt = ubicacionRepository.findById(id);
		if (ubicacionOpt.isPresent()) {
			return ubicacionOpt.get();
		}
		return null;
	}

	public Ubicacion create(Ubicacion ubicacion) {
		return ubicacionRepository.save(ubicacion);
	}

	public Ubicacion update(Integer id, Ubicacion ubicacion) {
		Optional<Ubicacion> ubicacionOpt = ubicacionRepository.findById(id);
		if (!ubicacionOpt.isPresent()) {
			return null;
		}
		Ubicacion updatedUbicacion = ubicacionOpt.get();
		updatedUbicacion.setNombre(ubicacion.getNombre());
		return ubicacionRepository.save(updatedUbicacion);
	}

	public Ubicacion delete(Integer id) {
		Optional<Ubicacion> ubicacionOpt = ubicacionRepository.findById(id);
		if (ubicacionOpt.isPresent()) {
			Ubicacion ubicacion = ubicacionOpt.get();
			ubicacionRepository.delete(ubicacion);
			return ubicacion;
		}
		return null;
	}
}

package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Ubicacion;
import co.edu.ufps.repositories.ProgramacionRepository;
import co.edu.ufps.repositories.SesionRepository;
import co.edu.ufps.repositories.UbicacionRepository;

@Service
public class UbicacionService {
	@Autowired
	private UbicacionRepository ubicacionRepository;
	
	@Autowired
	private ProgramacionRepository programacionRepository;
	
	@Autowired
	private SesionRepository sesionRepository;

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
	    if (!ubicacionOpt.isPresent()) {
	        throw new IllegalArgumentException("La ubicacion con id " + id + " no existe.");
	    }
	    Ubicacion ubicacion = ubicacionOpt.get();
	    if (programacionRepository.existsByUbicacion(ubicacion)) {
	        throw new IllegalStateException("No se puede eliminar la ubicacion porque tiene programaciones relacionadas.");
	    }
	    if (sesionRepository.existsByUbicacion(ubicacion)) {
	        throw new IllegalStateException("No se puede eliminar la ubicacion porque tiene sesiones relacionadas.");
	    }
	    ubicacionRepository.delete(ubicacion);
	    return ubicacion;
	}
}

package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Programacion;
import co.edu.ufps.repositories.InscripcionRepository;
import co.edu.ufps.repositories.ProgramacionRepository;
import co.edu.ufps.repositories.SesionRepository;

@Service
public class ProgramacionService {
	@Autowired
	private ProgramacionRepository programacionRepository;
	
	@Autowired
	private InscripcionRepository inscripcionRepository;
	
	@Autowired
	private SesionRepository sesionRepository;

	public List<Programacion> list() {
		return programacionRepository.findAll();
	}
	
	public Programacion get(Integer id) {
		Optional<Programacion> programacionOpt = programacionRepository.findById(id);
		if (programacionOpt.isPresent()) {
			return programacionOpt.get();
		}
		return null;
	}

	public Programacion create(Programacion programacion) {
		return programacionRepository.save(programacion);
	}

	public Programacion update(Integer id, Programacion programacion) {
		Optional<Programacion> programacionOpt = programacionRepository.findById(id);
		if (!programacionOpt.isPresent()) {
			return null;
		}
		Programacion updatedProgramacion = programacionOpt.get();
		updatedProgramacion.setColegio(programacion.getColegio());
		updatedProgramacion.setTaller(programacion.getTaller());
		updatedProgramacion.setFecha_inicio(programacion.getFecha_inicio());
		updatedProgramacion.setFecha_fin(programacion.getFecha_fin());
		updatedProgramacion.setCantidad(programacion.getCantidad());
		updatedProgramacion.setObservacion(programacion.getObservacion());
		updatedProgramacion.setInstructor(programacion.getInstructor());
		updatedProgramacion.setGrado(programacion.getGrado());
		updatedProgramacion.setGrupo(programacion.getGrupo());
		updatedProgramacion.setUbicacion(programacion.getUbicacion());
		return programacionRepository.save(updatedProgramacion);
	}

	public Programacion delete(Integer id) {
	    Optional<Programacion> programacionOpt = programacionRepository.findById(id);
	    if (!programacionOpt.isPresent()) {
	        throw new IllegalArgumentException("La programacion con id " + id + " no existe.");
	    }
	    Programacion programacion = programacionOpt.get();
	    if (inscripcionRepository.existsByProgramacion(programacion)) {
	        throw new IllegalStateException("No se puede eliminar la programacion porque tiene inscripciones relacionadas.");
	    }
	    if (sesionRepository.existsByProgramacion(programacion)) {
	        throw new IllegalStateException("No se puede eliminar la programacion porque tiene sesiones relacionadas.");
	    }
	    programacionRepository.delete(programacion);
	    return programacion;
	}
}

	package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Inscripcion;
import co.edu.ufps.entities.Programacion;
import co.edu.ufps.repositories.InscripcionRepository;
import co.edu.ufps.repositories.ProgramacionRepository;

@Service
public class InscripcionService {
	@Autowired
	private InscripcionRepository inscripcionRepository;

	@Autowired
	private ProgramacionRepository programacionRepository;
	
	public List<Inscripcion> list() {
		return inscripcionRepository.findAll();
	}
	
	public List<Inscripcion> listByProgramacion_id(Integer programacion_id) {
		Optional<Programacion> programacionOpt = programacionRepository.findById(programacion_id);
		if (programacionOpt.isPresent()) {
			return programacionOpt.get().getInscripciones();
		}
		return null;
	}
	
	public Inscripcion get(Integer id) {
		Optional<Inscripcion> inscripcionOpt = inscripcionRepository.findById(id);
		if (inscripcionOpt.isPresent()) {
			return inscripcionOpt.get();
		}
		return null;
	}

	public Inscripcion create(Inscripcion inscripcion) {
		return inscripcionRepository.save(inscripcion);
	}

	public Inscripcion update(Integer id, Inscripcion inscripcion) {
		Optional<Inscripcion> inscripcionOpt = inscripcionRepository.findById(id);
		if (!inscripcionOpt.isPresent()) {
			return null;
		}
		Inscripcion updatedInscripcion = inscripcionOpt.get();
		updatedInscripcion.setParticipante(inscripcion.getParticipante());
		updatedInscripcion.setProgramacion(inscripcion.getProgramacion());
		updatedInscripcion.setFecha(inscripcion.getFecha());
		return inscripcionRepository.save(updatedInscripcion);
	}

	public Inscripcion delete(Integer id) {
		Optional<Inscripcion> inscripcionOpt = inscripcionRepository.findById(id);
		if (inscripcionOpt.isPresent()) {
			Inscripcion inscripcion = inscripcionOpt.get();
			inscripcionRepository.delete(inscripcion);
			return inscripcion;
		}
		return null;
	}
}

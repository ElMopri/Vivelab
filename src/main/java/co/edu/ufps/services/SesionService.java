package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Programacion;
import co.edu.ufps.entities.Sesion;
import co.edu.ufps.repositories.ProgramacionRepository;
import co.edu.ufps.repositories.SesionRepository;

@Service
public class SesionService {
	@Autowired
	private SesionRepository sesionRepository;
	
	@Autowired
	private ProgramacionRepository programacionRepository;

	public List<Sesion> list() {
		return sesionRepository.findAll();
	}

	public List<Sesion> listByProgramacion_id(Integer programacion_id) {
		Optional<Programacion> programacionOpt = programacionRepository.findById(programacion_id);
		if (programacionOpt.isPresent()) {
			return programacionOpt.get().getSesiones();
		}
		return null;
	}

	public Sesion get(Integer id) {
		Optional<Sesion> sesionOpt = sesionRepository.findById(id);
		if (sesionOpt.isPresent()) {
			return sesionOpt.get();
		}
		return null;
	}

	public Sesion create(Sesion sesion) {
		return sesionRepository.save(sesion);
	}

	public Sesion update(Integer id, Sesion sesion) {
		Optional<Sesion> sesionOpt = sesionRepository.findById(id);
		if (!sesionOpt.isPresent()) {
			return null;
		}
		Sesion updatedSesion = sesionOpt.get();
		updatedSesion.setFecha(sesion.getFecha());
		updatedSesion.setHora(sesion.getHora());
		updatedSesion.setProgramacion_id(sesion.getProgramacion_id());
		updatedSesion.setInstructor_id(sesion.getInstructor_id());
		return sesionRepository.save(updatedSesion);
	}

	public Sesion delete(Integer id) {
		Optional<Sesion> sesionOpt = sesionRepository.findById(id);
		if (sesionOpt.isPresent()) {
			Sesion sesion = sesionOpt.get();
			sesionRepository.delete(sesion);
			return sesion;
		}
		return null;
	}
}

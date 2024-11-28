package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Asistente;
import co.edu.ufps.entities.Participante;
import co.edu.ufps.entities.Programacion;
import co.edu.ufps.entities.Sesion;
import co.edu.ufps.repositories.AsistenteRepository;
import co.edu.ufps.repositories.EvidenciaRepository;
import co.edu.ufps.repositories.ParticipanteRepository;
import co.edu.ufps.repositories.ProgramacionRepository;
import co.edu.ufps.repositories.SesionRepository;

@Service
public class SesionService {
	@Autowired
	private SesionRepository sesionRepository;
	
	@Autowired
	private ProgramacionRepository programacionRepository;
	
	@Autowired
	private AsistenteRepository asistenteRepository;

	@Autowired
	private ParticipanteRepository participanteRepository;
	
	@Autowired
	private EvidenciaRepository evidenciaRepository;
	
	public List<Sesion> list() {
		return sesionRepository.findAll();
	}

	public List<Sesion> listByProgramacionId(Integer programacionId) {
		Optional<Programacion> programacionOpt = programacionRepository.findById(programacionId);
		if (programacionOpt.isPresent()) {
			return programacionOpt.get().getSesiones();
		}
		return null;
	}
	
	public Asistente registrarAsistencia(Integer sesionId, Integer participanteId) {
		Optional<Sesion> sesionOpt = sesionRepository.findById(sesionId);
		Optional<Participante> participanteOpt = participanteRepository.findById(participanteId);
		if (sesionOpt.isPresent() && participanteOpt.isPresent()) {
			Sesion sesion = sesionOpt.get();
			Participante participante = participanteOpt.get();
			if(!asistenteRepository.existsBySesionAndParticipante(sesion, participante)) {
				Asistente asistente = new Asistente(sesion, participante);
				return asistenteRepository.save(asistente);
			}
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
		updatedSesion.setProgramacion(sesion.getProgramacion());
		updatedSesion.setInstructor(sesion.getInstructor());
		return sesionRepository.save(updatedSesion);
	}

	public Sesion delete(Integer id) {
	    Optional<Sesion> sesionOpt = sesionRepository.findById(id);
	    if (!sesionOpt.isPresent()) {
	        throw new IllegalArgumentException("La sesión con id " + id + " no existe.");
	    }
	    Sesion sesion = sesionOpt.get();
	    if (asistenteRepository.existsBySesion(sesion)) {
	        throw new IllegalStateException("No se puede eliminar la sesión porque tiene asistentes relacionados.");
	    }
	    if (evidenciaRepository.existsBySesion(sesion)) {
	        throw new IllegalStateException("No se puede eliminar la sesión porque tiene evidencias relacionadas.");
	    }
	    sesionRepository.delete(sesion);
	    return sesion;
	}
}

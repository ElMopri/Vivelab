package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Asistente;
import co.edu.ufps.entities.Inscripcion;
import co.edu.ufps.entities.Participante;
import co.edu.ufps.entities.Programacion;
import co.edu.ufps.entities.Sesion;
import co.edu.ufps.repositories.AsistenteRepository;
import co.edu.ufps.repositories.EvidenciaRepository;
import co.edu.ufps.repositories.InscripcionRepository;
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
	
	@Autowired
	private InscripcionRepository inscripcionRepository;
	
	public List<Sesion> list() {
		return sesionRepository.findAll();
	}
	
	public List<Sesion> listByProgramacionTallerNombre(String nombre) {
		return sesionRepository.findAllByProgramacion_Taller_NombreContainingIgnoreCase(nombre);
	}

	public List<Sesion> listByProgramacionId(Integer programacionId) {
		Optional<Programacion> programacionOpt = programacionRepository.findById(programacionId);
		if (!programacionOpt.isPresent()) {
			throw new IllegalArgumentException("No existe una programación con id " + programacionId);
		}
		return programacionOpt.get().getSesiones();
	}
	
	public Asistente registrarAsistencia(Integer sesionId, Integer participanteId) {
		Optional<Sesion> sesionOpt = sesionRepository.findById(sesionId);
		Optional<Participante> participanteOpt = participanteRepository.findById(participanteId);

		if (!sesionOpt.isPresent()) {
			throw new IllegalArgumentException("No existe una sesión con id " + sesionId);
		}

		if (!participanteOpt.isPresent()) {
			throw new IllegalArgumentException("No existe un participante con id " + participanteId);
		}

		Sesion sesion = sesionOpt.get();
		Participante participante = participanteOpt.get();

		if (asistenteRepository.existsBySesionAndParticipante(sesion, participante)) {
			throw new IllegalStateException("El participante ya está registrado en esta sesión.");
		}

		Asistente asistente = new Asistente(sesion, participante);
		return asistenteRepository.save(asistente);
	}

	public Sesion get(Integer id) {
		return sesionRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe una sesión con id " + id));
	}

	public Sesion create(Sesion sesion) {
		return sesionRepository.save(sesion);
	}

	public Sesion update(Integer id, Sesion sesion) {
		Optional<Sesion> sesionOpt = sesionRepository.findById(id);
		if (!sesionOpt.isPresent()) {
			throw new IllegalArgumentException("No existe una sesión con id " + id);
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
	        throw new IllegalArgumentException("No existe una sesión con id " + id);
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
	
    public Double porcentajeAsistencia(Integer sesionId) {
	    Optional<Sesion> sesionOpt = sesionRepository.findById(sesionId);
	    if (!sesionOpt.isPresent()) {
	        throw new IllegalArgumentException("No existe una sesión con id " + sesionId);
	    }
	    Sesion sesion = sesionOpt.get();
        Programacion programacion = sesion.getProgramacion();
        List<Inscripcion> inscripciones = inscripcionRepository.findByProgramacion(programacion);
        List<Integer> participantesInscritosIds = inscripciones.stream()
                .map(inscripcion -> inscripcion.getParticipante().getId())
                .collect(Collectors.toList());
        List<Asistente> asistentes = asistenteRepository.findBySesion(sesion);
        long participantesAsistentes = asistentes.stream()
                .filter(asistente -> participantesInscritosIds.contains(asistente.getParticipante().getId()))
                .count();
        double totalInscritos = participantesInscritosIds.size();
        if (totalInscritos == 0) {
            return 0.0;
        }
        double porcentaje = ((double)participantesAsistentes / totalInscritos) * 100;
        return porcentaje;
    }
}
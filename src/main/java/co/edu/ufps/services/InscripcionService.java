package co.edu.ufps.services;

import java.time.LocalDate;
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

    public List<Inscripcion> listByProgramacionId(Integer programacionId) {
        Optional<Programacion> programacionOpt = programacionRepository.findById(programacionId);
        if (programacionOpt.isPresent()) {
            return programacionOpt.get().getInscripciones();
        } else {
            throw new RuntimeException("Programación no encontrada con ID: " + programacionId);
        }
    }
    
    public List<Inscripcion> listByParticipanteNombre(String participanteNombre) {
    	return inscripcionRepository.findAllByParticipante_NombreContainingIgnoreCase(participanteNombre);
    }
    
    public List<Inscripcion> listByFecha(LocalDate fecha) {
        return inscripcionRepository.findAllByFecha(fecha);
    }

    public Inscripcion get(Integer id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada con ID: " + id));
    }

    public Inscripcion create(Inscripcion inscripcion) {
        if (inscripcionRepository.existsByParticipanteAndProgramacion(inscripcion.getParticipante(), inscripcion.getProgramacion())) {
            throw new IllegalArgumentException("Ya existe una inscripción con este participante y programación.");
        }
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion update(Integer id, Inscripcion inscripcion) {
        Optional<Inscripcion> existingInscripcionOpt = inscripcionRepository.findById(id);
        if (!existingInscripcionOpt.isPresent()) {
            throw new RuntimeException("Inscripción no encontrada para actualizar con ID: " + id);
        }
        if (inscripcionRepository.existsByParticipanteAndProgramacion(inscripcion.getParticipante(), inscripcion.getProgramacion())) {
            throw new IllegalArgumentException("Ya existe otra inscripción con este participante y programación.");
        }
        Inscripcion existingInscripcion = existingInscripcionOpt.get();
        existingInscripcion.setParticipante(inscripcion.getParticipante());
        existingInscripcion.setProgramacion(inscripcion.getProgramacion());
        existingInscripcion.setFecha(inscripcion.getFecha());
        return inscripcionRepository.save(existingInscripcion);
    }

    public Inscripcion delete(Integer id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada para eliminar con ID: " + id));
        inscripcionRepository.delete(inscripcion);
        return inscripcion;
    }
}

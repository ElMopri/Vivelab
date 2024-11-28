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

    public List<Inscripcion> listByProgramacionId(Integer programacionId) {
        Optional<Programacion> programacionOpt = programacionRepository.findById(programacionId);
        if (programacionOpt.isPresent()) {
            return programacionOpt.get().getInscripciones();
        } else {
            throw new RuntimeException("Programación no encontrada con ID: " + programacionId);
        }
    }

    public Inscripcion get(Integer id) {
        Optional<Inscripcion> inscripcionOpt = inscripcionRepository.findById(id);
        if (inscripcionOpt.isPresent()) {
            return inscripcionOpt.get();
        } else {
            throw new RuntimeException("Inscripción no encontrada con ID: " + id);
        }
    }

    public Inscripcion create(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion update(Integer id, Inscripcion inscripcion) {
        Optional<Inscripcion> inscripcionOpt = inscripcionRepository.findById(id);
        if (!inscripcionOpt.isPresent()) {
            throw new RuntimeException("Inscripción no encontrada para actualizar con ID: " + id);
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
        } else {
            throw new RuntimeException("Inscripción no encontrada para eliminar con ID: " + id);
        }
    }
}

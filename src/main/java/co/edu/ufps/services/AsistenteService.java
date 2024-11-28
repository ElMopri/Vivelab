package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Asistente;
import co.edu.ufps.repositories.AsistenteRepository;

@Service
public class AsistenteService {

    @Autowired
    private AsistenteRepository asistenteRepository;

    public List<Asistente> list() {
        try {
            return asistenteRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de asistentes: " + e.getMessage(), e);
        }
    }

    public Asistente get(Integer id) {
        Optional<Asistente> asistenteOpt = asistenteRepository.findById(id);
        if (asistenteOpt.isPresent()) {
            return asistenteOpt.get();
        }
        throw new IllegalArgumentException("El asistente con id " + id + " no existe.");
    }

    public Asistente create(Asistente asistente) {
        if (asistenteRepository.existsBySesionAndParticipante(asistente.getSesion(), asistente.getParticipante())) {
            throw new IllegalArgumentException("Ya existe un asistente con esta sesión y participante.");
        }
        try {
            return asistenteRepository.save(asistente);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error al guardar el asistente: Violación de restricciones de base de datos.");
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el asistente.");
        }
    }

    public Asistente update(Integer id, Asistente asistente) {
        Optional<Asistente> asistenteOpt = asistenteRepository.findById(id);
        if (!asistenteOpt.isPresent()) {
            throw new IllegalArgumentException("El asistente con id " + id + " no existe.");
        }
        if (asistenteRepository.existsBySesionAndParticipante(asistente.getSesion(), asistente.getParticipante())) {
            throw new IllegalArgumentException("Ya existe un asistente con esta sesión y participante.");
        }
        Asistente updatedAsistente = asistenteOpt.get();
        updatedAsistente.setSesion(asistente.getSesion());
        updatedAsistente.setParticipante(asistente.getParticipante());
        return asistenteRepository.save(updatedAsistente);
    }

    public Asistente delete(Integer id) {
        Optional<Asistente> asistenteOpt = asistenteRepository.findById(id);
        if (!asistenteOpt.isPresent()) {
            throw new IllegalArgumentException("El asistente con id " + id + " no existe.");
        }
        Asistente asistente = asistenteOpt.get();
        asistenteRepository.delete(asistente);
        return asistente;
    }
}

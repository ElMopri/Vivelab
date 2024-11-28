package co.edu.ufps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Colegio;
import co.edu.ufps.repositories.ColegioRepository;
import co.edu.ufps.repositories.ParticipanteRepository;
import co.edu.ufps.repositories.ProgramacionRepository;

@Service
public class ColegioService {
    @Autowired
    private ColegioRepository colegioRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private ProgramacionRepository programacionRepository;

    public List<Colegio> list() {
        return colegioRepository.findAll();
    }

    public Colegio get(Integer id) {
        return colegioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El colegio con id " + id + " no existe."));
    }

    public Colegio create(Colegio colegio) {
        return colegioRepository.save(colegio);
    }

    public Colegio update(Integer id, Colegio colegio) {
        Colegio existingColegio = colegioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El colegio con id " + id + " no existe."));
        
        existingColegio.setNombre(colegio.getNombre());
        existingColegio.setMunicipio(colegio.getMunicipio());
        existingColegio.setDane(colegio.getDane());
        return colegioRepository.save(existingColegio);
    }

    public Colegio delete(Integer id) {
        Colegio colegio = colegioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El colegio con id " + id + " no existe."));

        if (participanteRepository.existsByColegio(colegio)) {
            throw new IllegalStateException("No se puede eliminar el colegio porque tiene participantes relacionados.");
        }

        if (programacionRepository.existsByColegio(colegio)) {
            throw new IllegalStateException("No se puede eliminar el colegio porque tiene programaciones relacionadas.");
        }

        colegioRepository.delete(colegio);
        return colegio;
    }
}

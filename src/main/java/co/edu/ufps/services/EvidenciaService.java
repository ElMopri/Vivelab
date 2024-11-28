package co.edu.ufps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Evidencia;
import co.edu.ufps.entities.Sesion;
import co.edu.ufps.repositories.EvidenciaRepository;
import co.edu.ufps.repositories.SesionRepository;

@Service
public class EvidenciaService {
    @Autowired
    private EvidenciaRepository evidenciaRepository;

    @Autowired
    private SesionRepository sesionRepository;

    public List<Evidencia> list() {
        return evidenciaRepository.findAll();
    }

    public List<Evidencia> listBySesionId(Integer sesionId) {
        Sesion sesion = sesionRepository.findById(sesionId)
                .orElseThrow(() -> new IllegalArgumentException("La sesión con id " + sesionId + " no existe."));
        return sesion.getEvidencias();
    }

    public Evidencia get(Integer id) {
        return evidenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La evidencia con id " + id + " no existe."));
    }

    public Evidencia create(Evidencia evidencia) {
        if (evidencia.getSesion() == null || !sesionRepository.existsById(evidencia.getSesion().getId())) {
            throw new IllegalArgumentException("La sesión asociada a la evidencia no existe.");
        }
        return evidenciaRepository.save(evidencia);
    }

    public Evidencia update(Integer id, Evidencia evidencia) {
        Evidencia existingEvidencia = evidenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La evidencia con id " + id + " no existe."));
        
        if (evidencia.getSesion() != null && !sesionRepository.existsById(evidencia.getSesion().getId())) {
            throw new IllegalArgumentException("La sesión asociada a la evidencia no existe.");
        }

        existingEvidencia.setSesion(evidencia.getSesion());
        existingEvidencia.setObservaciones(evidencia.getObservaciones());
        existingEvidencia.setFecha(evidencia.getFecha());
        existingEvidencia.setArchivo(evidencia.getArchivo());
        return evidenciaRepository.save(existingEvidencia);
    }

    public Evidencia delete(Integer id) {
        Evidencia evidencia = evidenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La evidencia con id " + id + " no existe."));
        evidenciaRepository.delete(evidencia);
        return evidencia;
    }
}

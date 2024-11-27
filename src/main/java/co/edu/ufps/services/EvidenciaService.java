package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

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
		Optional<Sesion> sesionOpt = sesionRepository.findById(sesionId);
		if (sesionOpt.isPresent()) {
			return sesionOpt.get().getEvidencias();
		}
		return null;
	}
	
	public Evidencia get(Integer id) {
		Optional<Evidencia> evidenciaOpt = evidenciaRepository.findById(id);
		if (evidenciaOpt.isPresent()) {
			return evidenciaOpt.get();
		}
		return null;
	}

	public Evidencia create(Evidencia evidencia) {
		return evidenciaRepository.save(evidencia);
	}

	public Evidencia update(Integer id, Evidencia evidencia) {
		Optional<Evidencia> evidenciaOpt = evidenciaRepository.findById(id);
		if (!evidenciaOpt.isPresent()) {
			return null;
		}
		Evidencia updatedEvidencia = evidenciaOpt.get();
		updatedEvidencia.setSesion(evidencia.getSesion());
		updatedEvidencia.setObservaciones(evidencia.getObservaciones());
		updatedEvidencia.setFecha(evidencia.getFecha());
		return evidenciaRepository.save(updatedEvidencia);
	}

	public Evidencia delete(Integer id) {
		Optional<Evidencia> evidenciaOpt = evidenciaRepository.findById(id);
		if (evidenciaOpt.isPresent()) {
			Evidencia evidencia = evidenciaOpt.get();
			evidenciaRepository.delete(evidencia);
			return evidencia;
		}
		return null;
	}
}

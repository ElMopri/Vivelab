package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.TipoDocumento;
import co.edu.ufps.repositories.ParticipanteRepository;
import co.edu.ufps.repositories.TipoDocumentoRepository;

@Service
public class TipoDocumentoService {
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	@Autowired
	private ParticipanteRepository participanteRepository;

	public List<TipoDocumento> list() {
		return tipoDocumentoRepository.findAll();
	}
	
	public TipoDocumento get(Integer id) {
		Optional<TipoDocumento> tipoDocumentoOpt = tipoDocumentoRepository.findById(id);
		if (tipoDocumentoOpt.isPresent()) {
			return tipoDocumentoOpt.get();
		}
		return null;
	}

	public TipoDocumento create(TipoDocumento tipoDocumento) {
		return tipoDocumentoRepository.save(tipoDocumento);
	}

	public TipoDocumento update(Integer id, TipoDocumento tipoDocumento) {
		Optional<TipoDocumento> tipoDocumentoOpt = tipoDocumentoRepository.findById(id);
		if (!tipoDocumentoOpt.isPresent()) {
			return null;
		}
		TipoDocumento updatedTipoDocumento = tipoDocumentoOpt.get();
		updatedTipoDocumento.setDescripcion(tipoDocumento.getDescripcion());
		return tipoDocumentoRepository.save(updatedTipoDocumento);
	}

	public TipoDocumento delete(Integer id) {
	    Optional<TipoDocumento> tipoDocumentoOpt = tipoDocumentoRepository.findById(id);
	    if (!tipoDocumentoOpt.isPresent()) {
	        throw new IllegalArgumentException("El tipo de documento con id " + id + " no existe.");
	    }
	    TipoDocumento tipoDocumento = tipoDocumentoOpt.get();
	    if (participanteRepository.existsByTipoDocumento(tipoDocumento)) {
	        throw new IllegalStateException("No se puede eliminar el tipo de documento porque tiene participantes relacionados.");
	    }
	    tipoDocumentoRepository.delete(tipoDocumento);
	    return tipoDocumento;
	}
}

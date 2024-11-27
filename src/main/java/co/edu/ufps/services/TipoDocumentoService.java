package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.TipoDocumento;
import co.edu.ufps.repositories.TipoDocumentoRepository;

@Service
public class TipoDocumentoService {
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

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

	public TipoDocumento update(Integer id, TipoDocumento tipoDocumentoDetails) {
		Optional<TipoDocumento> tipoDocumentoOpt = tipoDocumentoRepository.findById(id);
		if (!tipoDocumentoOpt.isPresent()) {
			return null;
		}
		TipoDocumento tipoDocumento = tipoDocumentoOpt.get();
		tipoDocumento.setDescripcion(tipoDocumentoDetails.getDescripcion());
		return tipoDocumentoRepository.save(tipoDocumento);
	}

	public TipoDocumento delete(Integer id) {
		Optional<TipoDocumento> tipoDocumentoOpt = tipoDocumentoRepository.findById(id);
		if (tipoDocumentoOpt.isPresent()) {
			TipoDocumento tipoDocumento = tipoDocumentoOpt.get();
			tipoDocumentoRepository.delete(tipoDocumento);
			return tipoDocumento;
		}
		return null;
	}
}

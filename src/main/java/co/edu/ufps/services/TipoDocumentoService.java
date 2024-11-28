package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            return tipoDocumentoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de tipos de documentos: " + e.getMessage(), e);
        }
    }
    
    public TipoDocumento get(Integer id) {
        Optional<TipoDocumento> tipoDocumentoOpt = tipoDocumentoRepository.findById(id);
        if (tipoDocumentoOpt.isPresent()) {
            return tipoDocumentoOpt.get();
        }
        throw new IllegalArgumentException("El tipo de documento con id " + id + " no existe.");
    }

    public TipoDocumento create(TipoDocumento tipoDocumento) {
        try {
            return tipoDocumentoRepository.save(tipoDocumento);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error al guardar el tipo de documento: Violación de restricciones de base de datos.");
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el tipo de documento.");
        }
    }

    public TipoDocumento update(Integer id, TipoDocumento tipoDocumento) {
        Optional<TipoDocumento> tipoDocumentoOpt = tipoDocumentoRepository.findById(id);
        if (!tipoDocumentoOpt.isPresent()) {
            throw new IllegalArgumentException("El tipo de documento con id " + id + " no existe.");
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
        try {
            tipoDocumentoRepository.delete(tipoDocumento);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el tipo de documento.");
        }
        return tipoDocumento;
    }
}

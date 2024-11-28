package co.edu.ufps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.TipoDocumento;
import co.edu.ufps.services.TipoDocumentoService;

@RestController
@RequestMapping("/api/tipo_documentos")
public class TipoDocumentoController {
	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	
	@GetMapping
	public ResponseEntity<List<TipoDocumento>> list() {
		return ResponseEntity.ok(tipoDocumentoService.list());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoDocumento> get(@PathVariable Integer id) {
		return ResponseEntity.ok(tipoDocumentoService.get(id));
	}
	
	@PostMapping()
	public ResponseEntity<TipoDocumento> create(@RequestBody TipoDocumento tipoDocumento) {
		TipoDocumento newTipoDocumento = tipoDocumentoService.create(tipoDocumento);
		return ResponseEntity.ok(newTipoDocumento);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TipoDocumento> update(@PathVariable Integer id, @RequestBody TipoDocumento tipoDocumento) {
		TipoDocumento updatedTipoDocumento = tipoDocumentoService.update(id, tipoDocumento);
		return ResponseEntity.ok(updatedTipoDocumento);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
	    try {
	        TipoDocumento deletedTipoDocumento = tipoDocumentoService.delete(id);
	        return ResponseEntity.ok(deletedTipoDocumento);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (IllegalStateException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
}

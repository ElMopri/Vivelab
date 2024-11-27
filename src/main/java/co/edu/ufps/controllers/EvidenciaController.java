package co.edu.ufps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.Evidencia;
import co.edu.ufps.services.EvidenciaService;

@RestController
@RequestMapping("/api/evidencias")
public class EvidenciaController {
	@Autowired
	private EvidenciaService evidenciaService;
	
	@GetMapping
	public ResponseEntity<List<Evidencia>> list() {
		return ResponseEntity.ok(evidenciaService.list());
	}
	
		@GetMapping("/sesiones/{sesionId}")
	public ResponseEntity<List<Evidencia>> listBySesionId(@PathVariable Integer sesionId) {
		return ResponseEntity.ok(evidenciaService.listBySesionId(sesionId));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Evidencia> get(@PathVariable Integer id) {
		return ResponseEntity.ok(evidenciaService.get(id));
	}
	
	@PostMapping()
	public ResponseEntity<Evidencia> create(@RequestBody Evidencia evidencia) {
		Evidencia newEvidencia = evidenciaService.create(evidencia);
		return ResponseEntity.ok(newEvidencia);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Evidencia> update(@PathVariable Integer id, @RequestBody Evidencia evidencia) {
		Evidencia updatedEvidencia = evidenciaService.update(id, evidencia);
		return ResponseEntity.ok(updatedEvidencia);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Evidencia> delete(@PathVariable Integer id) {
		Evidencia deletedEvidencia = evidenciaService.delete(id);
		return ResponseEntity.ok(deletedEvidencia);
	}
}

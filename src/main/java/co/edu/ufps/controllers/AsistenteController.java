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

import co.edu.ufps.entities.Asistente;
import co.edu.ufps.services.AsistenteService;

@RestController
@RequestMapping("/api/asistentes")
public class AsistenteController {
	@Autowired
	private AsistenteService asistenteService;
	
	@GetMapping
	public ResponseEntity<List<Asistente>> list() {
		return ResponseEntity.ok(asistenteService.list());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Asistente> get(@PathVariable Integer id) {
		return ResponseEntity.ok(asistenteService.get(id));
	}
	
	@PostMapping()
	public ResponseEntity<Asistente> create(@RequestBody Asistente asistente) {
		Asistente newAsistente = asistenteService.create(asistente);
		return ResponseEntity.ok(newAsistente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Asistente> update(@PathVariable Integer id, @RequestBody Asistente asistente) {
		Asistente updatedAsistente = asistenteService.update(id, asistente);
		return ResponseEntity.ok(updatedAsistente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Asistente> delete(@PathVariable Integer id) {
		Asistente deletedAsistente = asistenteService.delete(id);
		return ResponseEntity.ok(deletedAsistente);
	}
}

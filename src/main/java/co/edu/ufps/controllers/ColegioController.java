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

import co.edu.ufps.entities.Colegio;
import co.edu.ufps.services.ColegioService;

@RestController
@RequestMapping("/api/colegios")
public class ColegioController {
	@Autowired
	private ColegioService colegioService;
	
	@GetMapping
	public ResponseEntity<List<Colegio>> list() {
		return ResponseEntity.ok(colegioService.list());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Colegio> get(@PathVariable Integer id) {
		return ResponseEntity.ok(colegioService.get(id));
	}
	
	@PostMapping()
	public ResponseEntity<Colegio> create(@RequestBody Colegio colegio) {
		Colegio newColegio = colegioService.create(colegio);
		return ResponseEntity.ok(newColegio);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Colegio> update(@PathVariable Integer id, @RequestBody Colegio colegio) {
		Colegio updatedColegio = colegioService.update(id, colegio);
		return ResponseEntity.ok(updatedColegio);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Colegio> delete(@PathVariable Integer id) {
		Colegio deletedColegio = colegioService.delete(id);
		return ResponseEntity.ok(deletedColegio);
	}
}

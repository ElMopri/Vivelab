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
	public ResponseEntity<?> get(@PathVariable Integer id) {
		try {
			Colegio colegio = colegioService.get(id);
			return ResponseEntity.ok(colegio);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<Colegio> create(@RequestBody Colegio colegio) {
		Colegio newColegio = colegioService.create(colegio);
		return ResponseEntity.status(HttpStatus.CREATED).body(newColegio);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Colegio colegio) {
		try {
			Colegio updatedColegio = colegioService.update(id, colegio);
			return ResponseEntity.ok(updatedColegio);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			Colegio deletedColegio = colegioService.delete(id);
			return ResponseEntity.ok(deletedColegio);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}

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

import co.edu.ufps.entities.Taller;
import co.edu.ufps.services.TallerService;

@RestController
@RequestMapping("/api/talleres")
public class TallerController {
	@Autowired
	private TallerService tallerService;
	
	@GetMapping
	public ResponseEntity<List<Taller>> list() {
		return ResponseEntity.ok(tallerService.list());
	}
	
	@GetMapping("/listByNombre/{nombre}")
	public ResponseEntity<List<Taller>> listByNombre(@PathVariable String nombre) {
		return ResponseEntity.ok(tallerService.listByNombre(nombre));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Taller> get(@PathVariable Integer id) {
		return ResponseEntity.ok(tallerService.get(id));
	}
	
	@PostMapping()
	public ResponseEntity<Taller> create(@RequestBody Taller taller) {
		Taller newTaller = tallerService.create(taller);
		return ResponseEntity.ok(newTaller);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Taller> update(@PathVariable Integer id, @RequestBody Taller taller) {
		Taller updatedTaller = tallerService.update(id, taller);
		return ResponseEntity.ok(updatedTaller);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
	    try {
	        Taller deletedTaller = tallerService.delete(id);
	        return ResponseEntity.ok(deletedTaller);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (IllegalStateException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
}

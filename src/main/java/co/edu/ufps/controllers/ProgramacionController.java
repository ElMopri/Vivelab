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

import co.edu.ufps.entities.Programacion;
import co.edu.ufps.services.ProgramacionService;

@RestController
@RequestMapping("/api/programaciones")
public class ProgramacionController {
	@Autowired
	private ProgramacionService programacionService;
	
	@GetMapping
	public ResponseEntity<List<Programacion>> list() {
		return ResponseEntity.ok(programacionService.list());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Programacion> get(@PathVariable Integer id) {
		return ResponseEntity.ok(programacionService.get(id));
	}
	
	@PostMapping()
	public ResponseEntity<Programacion> create(@RequestBody Programacion programacion) {
		Programacion newProgramacion = programacionService.create(programacion);
		return ResponseEntity.ok(newProgramacion);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Programacion> update(@PathVariable Integer id, @RequestBody Programacion programacion) {
		Programacion updatedProgramacion = programacionService.update(id, programacion);
		return ResponseEntity.ok(updatedProgramacion);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
	    try {
	        Programacion deletedProgramacion = programacionService.delete(id);
	        return ResponseEntity.ok(deletedProgramacion);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (IllegalStateException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
}

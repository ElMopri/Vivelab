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

import co.edu.ufps.entities.Sesion;
import co.edu.ufps.services.SesionService;

@RestController
@RequestMapping("/api/sesiones")
public class SesionController {
	@Autowired
	private SesionService sesionService;

	@GetMapping
	public ResponseEntity<List<Sesion>> list() {
		return ResponseEntity.ok(sesionService.list());
	}

	@GetMapping("/programaciones/{programacionId}")
	public ResponseEntity<?> listByProgramacionId(@PathVariable Integer programacionId) {
	    try {
	        return ResponseEntity.ok(sesionService.listByProgramacionId(programacionId));
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}

	@PostMapping("/{sesionId}/asistencias")
	public ResponseEntity<?> registrarAsistencia(@PathVariable Integer sesionId, @RequestBody Integer participanteId) {
	    try {
	        return ResponseEntity.ok(sesionService.registrarAsistencia(sesionId, participanteId));
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (IllegalStateException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Integer id) {
	    try {
	        return ResponseEntity.ok(sesionService.get(id));
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}

	@PostMapping()
	public ResponseEntity<Sesion> create(@RequestBody Sesion sesion) {
		Sesion newSesion = sesionService.create(sesion);
		return ResponseEntity.ok(newSesion);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Sesion sesion) {
	    try {
	        return ResponseEntity.ok(sesionService.update(id, sesion));
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
	    try {
	        Sesion deletedSesion = sesionService.delete(id);
	        return ResponseEntity.ok(deletedSesion);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (IllegalStateException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
}
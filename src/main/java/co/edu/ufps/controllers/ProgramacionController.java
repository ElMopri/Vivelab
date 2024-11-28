package co.edu.ufps.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/listByNombreTallerOrFechaInicioOrFechaFin")
	public ResponseEntity<?> listByNombreTallerOrFechaInicioOrFechaFin(
	        @RequestParam(required = false) String nombre,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
	    try {
	        List<Programacion> programaciones = programacionService.listByNombreTallerOrFechaInicioOrFechaFin(nombre, fechaInicio, fechaFin);
	        return ResponseEntity.ok(programaciones);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Integer id) {
	    try {
	        return ResponseEntity.ok(programacionService.get(id));
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}
	
	@PostMapping()
	public ResponseEntity<Programacion> create(@RequestBody Programacion programacion) {
		Programacion newProgramacion = programacionService.create(programacion);
		return ResponseEntity.ok(newProgramacion);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Programacion programacion) {
	    try {
	        return ResponseEntity.ok(programacionService.update(id, programacion));
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
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
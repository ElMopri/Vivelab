package co.edu.ufps.controllers;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.edu.ufps.entities.Inscripcion;
import co.edu.ufps.services.InscripcionService;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

	@Autowired
	private InscripcionService inscripcionService;

	@GetMapping
    public ResponseEntity<List<Inscripcion>> list() {
        return ResponseEntity.ok(inscripcionService.list());
	}

	@GetMapping("/listByParticipanteNombre/{participanteNombre}")
	public ResponseEntity<List<Inscripcion>> listByParticipanteNombre(@PathVariable String participanteNombre) {
		return ResponseEntity.ok(inscripcionService.listByParticipanteNombre(participanteNombre));
	}
	
	@GetMapping("/listByFecha/{fecha}")
	public ResponseEntity<List<Inscripcion>> listByFecha(@PathVariable LocalDate fecha) {
		return ResponseEntity.ok(inscripcionService.listByFecha(fecha));
	}

	@GetMapping("/programaciones/{programacionId}")
	public ResponseEntity<List<Inscripcion>> listByProgramacionId(@PathVariable Integer programacionId) {
		return ResponseEntity.ok(inscripcionService.listByProgramacionId(programacionId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Inscripcion> get(@PathVariable Integer id) {
		return ResponseEntity.ok(inscripcionService.get(id));
	}

	@PostMapping()
	public ResponseEntity<Inscripcion> create(@RequestBody Inscripcion inscripcion) {
		Inscripcion newInscripcion = inscripcionService.create(inscripcion);
		return ResponseEntity.status(HttpStatus.CREATED).body(newInscripcion);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Inscripcion> update(@PathVariable Integer id, @RequestBody Inscripcion inscripcion) {
		Inscripcion updatedInscripcion = inscripcionService.update(id, inscripcion);
		return ResponseEntity.ok(updatedInscripcion);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Inscripcion> delete(@PathVariable Integer id) {
		Inscripcion deletedInscripcion = inscripcionService.delete(id);
		return ResponseEntity.ok(deletedInscripcion);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}

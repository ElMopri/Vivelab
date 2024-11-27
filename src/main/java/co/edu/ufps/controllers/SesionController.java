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

	@GetMapping("/{programacion_id}")
	public ResponseEntity<List<Sesion>> listByProgramacion_id(@PathVariable Integer programacion_id) {
		return ResponseEntity.ok(sesionService.listByProgramacion_id(programacion_id));
	}

	@PostMapping("/{sesion_id}/asistencias")
	public ResponseEntity<Sesion> registrarAsistencia(@PathVariable Integer sesion_id, @RequestBody Integer participante_id) {
		return ResponseEntity.ok(sesionService.registrarAsistencia(sesion_id, participante_id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Sesion> get(@PathVariable Integer id) {
		return ResponseEntity.ok(sesionService.get(id));
	}

	@PostMapping()
	public ResponseEntity<Sesion> create(@RequestBody Sesion sesion) {
		Sesion newSesion = sesionService.create(sesion);
		return ResponseEntity.ok(newSesion);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Sesion> update(@PathVariable Integer id, @RequestBody Sesion sesion) {
		Sesion updatedSesion = sesionService.update(id, sesion);
		return ResponseEntity.ok(updatedSesion);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Sesion> delete(@PathVariable Integer id) {
		Sesion deletedSesion = sesionService.delete(id);
		return ResponseEntity.ok(deletedSesion);
	}
}

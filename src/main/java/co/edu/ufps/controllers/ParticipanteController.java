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

import co.edu.ufps.entities.Participante;
import co.edu.ufps.services.ParticipanteService;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {
	@Autowired
	private ParticipanteService participanteService;
	
	@GetMapping
	public ResponseEntity<List<Participante>> list() {
		return ResponseEntity.ok(participanteService.list());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Participante> get(@PathVariable Integer id) {
		return ResponseEntity.ok(participanteService.get(id));
	}
	
	@PostMapping()
	public ResponseEntity<Participante> create(@RequestBody Participante participante) {
		Participante newParticipante = participanteService.create(participante);
		return ResponseEntity.ok(newParticipante);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Participante> update(@PathVariable Integer id, @RequestBody Participante participante) {
		Participante updatedParticipante = participanteService.update(id, participante);
		return ResponseEntity.ok(updatedParticipante);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
	    try {
	        Participante deletedParticipante = participanteService.delete(id);
	        return ResponseEntity.ok(deletedParticipante);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (IllegalStateException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
}

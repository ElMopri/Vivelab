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

import co.edu.ufps.entities.Instructor;
import co.edu.ufps.services.InstructorService;

@RestController
@RequestMapping("/api/instructores")
public class InstructorController {
	@Autowired
	private InstructorService instructorService;
	
	@GetMapping
	public ResponseEntity<List<Instructor>> list() {
		return ResponseEntity.ok(instructorService.list());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Instructor> get(@PathVariable Integer id) {
		return ResponseEntity.ok(instructorService.get(id));
	}
	
	@PostMapping()
	public ResponseEntity<Instructor> create(@RequestBody Instructor instructor) {
		Instructor newInstructor = instructorService.create(instructor);
		return ResponseEntity.ok(newInstructor);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Instructor> update(@PathVariable Integer id, @RequestBody Instructor instructor) {
		Instructor updatedInstructor = instructorService.update(id, instructor);
		return ResponseEntity.ok(updatedInstructor);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Instructor> delete(@PathVariable Integer id) {
		Instructor deletedInstructor = instructorService.delete(id);
		return ResponseEntity.ok(deletedInstructor);
	}
}

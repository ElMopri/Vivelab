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

import co.edu.ufps.entities.Instructor;
import co.edu.ufps.services.InstructorService;

@RestController
@RequestMapping("/api/instructores")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;
    
    @GetMapping
    public ResponseEntity<List<Instructor>> list() {
        List<Instructor> instructors = instructorService.list();
        return ResponseEntity.ok(instructors);
    }
    
    @GetMapping("/listByNombre/{nombre}")
    public ResponseEntity<List<Instructor>> listByNombre(@PathVariable String nombre) {
        List<Instructor> instructors = instructorService.listByNombre(nombre);
        return ResponseEntity.ok(instructors);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        try {
            Instructor instructor = instructorService.get(id);
            return ResponseEntity.ok(instructor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Instructor con id " + id + " no encontrado.");
        }
    }
    
    @GetMapping("/sesiones/{id}")
    public ResponseEntity<?> getSesiones(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(instructorService.get(id).getSesiones());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Instructor con id " + id + " no encontrado.");
        }
    }
    
    @PostMapping()
    public ResponseEntity<Instructor> create(@RequestBody Instructor instructor) {
        Instructor newInstructor = instructorService.create(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstructor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Instructor instructor) {
        try {
            Instructor updatedInstructor = instructorService.update(id, instructor);
            return ResponseEntity.ok(updatedInstructor);
        } catch (IllegalArgumentException e) {
            // Respuesta 404 si el instructor no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Instructor con id " + id + " no encontrado.");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Instructor deletedInstructor = instructorService.delete(id);
            return ResponseEntity.ok(deletedInstructor);
        } catch (IllegalArgumentException e) {
            // Respuesta 404 si el instructor no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("El instructor con id " + id + " no existe.");
        } catch (IllegalStateException e) {
            // Respuesta 400 si el instructor tiene dependencias asociadas
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(e.getMessage());
        }
    }
}

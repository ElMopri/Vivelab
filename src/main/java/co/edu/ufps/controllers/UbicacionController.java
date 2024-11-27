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

import co.edu.ufps.entities.Ubicacion;
import co.edu.ufps.services.UbicacionService;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {
	@Autowired
	private UbicacionService ubicacionService;
	
	@GetMapping
	public ResponseEntity<List<Ubicacion>> list() {
		return ResponseEntity.ok(ubicacionService.list());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ubicacion> get(@PathVariable Integer id) {
		return ResponseEntity.ok(ubicacionService.get(id));
	}
	
	@PostMapping()
	public ResponseEntity<Ubicacion> create(@RequestBody Ubicacion ubicacion) {
		Ubicacion newUbicacion = ubicacionService.create(ubicacion);
		return ResponseEntity.ok(newUbicacion);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Ubicacion> update(@PathVariable Integer id, @RequestBody Ubicacion ubicacionDetails) {
		Ubicacion updatedUbicacion = ubicacionService.update(id, ubicacionDetails);
		return ResponseEntity.ok(updatedUbicacion);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Ubicacion> delete(@PathVariable Integer id) {
		Ubicacion deletedUbicacion = ubicacionService.delete(id);
		return ResponseEntity.ok(deletedUbicacion);
	}
}

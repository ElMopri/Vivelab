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

import co.edu.ufps.entities.Municipio;
import co.edu.ufps.services.MunicipioService;

@RestController
@RequestMapping("/api/municipios")
public class MunicipioController {
	@Autowired
	private MunicipioService municipioService;
	
	@GetMapping
	public ResponseEntity<List<Municipio>> list() {
		return ResponseEntity.ok(municipioService.list());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Municipio> get(@PathVariable Integer id) {
		return ResponseEntity.ok(municipioService.get(id));
	}
	
	@PostMapping()
	public ResponseEntity<Municipio> create(@RequestBody Municipio municipio) {
		Municipio newMunicipio = municipioService.create(municipio);
		return ResponseEntity.ok(newMunicipio);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Municipio> update(@PathVariable Integer id, @RequestBody Municipio municipio) {
		Municipio updatedMunicipio = municipioService.update(id, municipio);
		return ResponseEntity.ok(updatedMunicipio);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Municipio> delete(@PathVariable Integer id) {
		Municipio deletedMunicipio = municipioService.delete(id);
		return ResponseEntity.ok(deletedMunicipio);
	}
}

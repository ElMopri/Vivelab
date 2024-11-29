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

import co.edu.ufps.entities.Rol;
import co.edu.ufps.entities.Usuario;
import co.edu.ufps.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@PutMapping("/cambiarRol/{usuarioId}/{instructorId}")
	public ResponseEntity<Usuario> setRol(@PathVariable Integer usuarioId, @PathVariable Integer instructorId) {
		Usuario updatedUsuario = usuarioService.setInstructor(usuarioId, instructorId);
		return ResponseEntity.ok(updatedUsuario);
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> list() {
		return ResponseEntity.ok(usuarioService.list());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> get(@PathVariable Integer id) {
		return ResponseEntity.ok(usuarioService.get(id));
	}

	@GetMapping("/{id}/roles")
	public ResponseEntity<List<Rol>> getRols(@PathVariable Integer id) {
		return ResponseEntity.ok(usuarioService.get(id).getRoles());
	}

	@PostMapping()
	public ResponseEntity<Usuario> create(@RequestBody Usuario tipoPersona) {
		Usuario nuevaUsuario = usuarioService.create(tipoPersona);
		return ResponseEntity.ok(nuevaUsuario);
	}

	@PostMapping("/{id}/agregar_roles/{rolId}")
	public ResponseEntity<Usuario> create(@PathVariable Integer id, @PathVariable Integer rolId) {
		Usuario nuevaUsuario = usuarioService.addRol(id, rolId);
		return ResponseEntity.ok(nuevaUsuario);
	}

	@DeleteMapping("/{id}/remover_roles/{rolId}")
	public ResponseEntity<Usuario> deleteRol(@PathVariable Integer id, @PathVariable Integer rolId) {
		Usuario nuevaUsuario = usuarioService.removeRol(id, rolId);
		return ResponseEntity.ok(nuevaUsuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Integer id,
			@RequestBody Usuario usuarioDetails) {
		Usuario updatedUsuario = usuarioService.update(id, usuarioDetails);
		return ResponseEntity.ok(updatedUsuario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable Integer id) {
		Usuario deletedUsuario = usuarioService.delete(id);
		return ResponseEntity.ok(deletedUsuario);
	}
}

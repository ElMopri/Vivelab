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

import co.edu.ufps.entities.Evidencia;
import co.edu.ufps.services.EvidenciaService;

@RestController
@RequestMapping("/api/evidencias")
public class EvidenciaController {
    @Autowired
    private EvidenciaService evidenciaService;

    @GetMapping
    public ResponseEntity<List<Evidencia>> list() {
        return ResponseEntity.ok(evidenciaService.list());
    }

    @GetMapping("/sesiones/{sesionId}")
    public ResponseEntity<?> listBySesionId(@PathVariable Integer sesionId) {
        try {
            List<Evidencia> evidencias = evidenciaService.listBySesionId(sesionId);
            return ResponseEntity.ok(evidencias);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        try {
            Evidencia evidencia = evidenciaService.get(id);
            return ResponseEntity.ok(evidencia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Evidencia evidencia) {
        try {
            Evidencia newEvidencia = evidenciaService.create(evidencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(newEvidencia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Evidencia evidencia) {
        try {
            Evidencia updatedEvidencia = evidenciaService.update(id, evidencia);
            return ResponseEntity.ok(updatedEvidencia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Evidencia deletedEvidencia = evidenciaService.delete(id);
            return ResponseEntity.ok(deletedEvidencia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

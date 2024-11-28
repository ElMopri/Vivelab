package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Taller;
import co.edu.ufps.repositories.ProgramacionRepository;
import co.edu.ufps.repositories.TallerRepository;

@Service
public class TallerService {
    @Autowired
    private TallerRepository tallerRepository;
    
    @Autowired
    private ProgramacionRepository programacionRepository;

    public List<Taller> list() {
        try {
            return tallerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de talleres: " + e.getMessage(), e);
        }
    }
    
    public List<Taller> listByNombre(String nombre) {
        try {
            return tallerRepository.findAllByNombreContainingIgnoreCase(nombre);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar talleres por nombre: " + e.getMessage(), e);
        }
    }
    
    public Taller get(Integer id) {
        Optional<Taller> tallerOpt = tallerRepository.findById(id);
        if (tallerOpt.isPresent()) {
            return tallerOpt.get();
        }
        throw new IllegalArgumentException("El taller con id " + id + " no existe.");
    }

    public Taller create(Taller taller) {
        try {
            return tallerRepository.save(taller);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error al guardar el taller: Violación de restricciones de base de datos.");
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el taller.");
        }
    }

    public Taller update(Integer id, Taller taller) {
        Optional<Taller> tallerOpt = tallerRepository.findById(id);
        if (!tallerOpt.isPresent()) {
            throw new IllegalArgumentException("El taller con id " + id + " no existe.");
        }
        Taller updatedTaller = tallerOpt.get();
        updatedTaller.setNombre(taller.getNombre());
        updatedTaller.setDescripcion(taller.getDescripcion());
        return tallerRepository.save(updatedTaller);
    }

    public Taller delete(Integer id) {
        Optional<Taller> tallerOpt = tallerRepository.findById(id);
        if (!tallerOpt.isPresent()) {
            throw new IllegalArgumentException("El taller con id " + id + " no existe.");
        }
        Taller taller = tallerOpt.get();
        if (programacionRepository.existsByTaller(taller)) {
            throw new IllegalStateException("No se puede eliminar el taller porque tiene programaciones relacionadas.");
        }
        try {
            tallerRepository.delete(taller);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el taller.");
        }
        return taller;
    }
}

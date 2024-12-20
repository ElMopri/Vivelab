package co.edu.ufps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Instructor;
import co.edu.ufps.repositories.InstructorRepository;
import co.edu.ufps.repositories.ProgramacionRepository;
import co.edu.ufps.repositories.SesionRepository;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    
    @Autowired
    private ProgramacionRepository programacionRepository;
    
    @Autowired
    private SesionRepository sesionRepository;

    public List<Instructor> list() {
        return instructorRepository.findAll();
    }
    
    public List<Instructor> listByNombre(String nombre) {
        return instructorRepository.findAllByNombreContainingIgnoreCase(nombre);
    }
    
    public Instructor get(Integer id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El instructor con id " + id + " no existe."));
    }

    public Instructor create(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor update(Integer id, Instructor instructor) {
        Instructor updatedInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El instructor con id " + id + " no existe."));
        
        updatedInstructor.setNombre(instructor.getNombre());
        updatedInstructor.setDocumento(instructor.getDocumento());
        return instructorRepository.save(updatedInstructor);
    }

    public Instructor delete(Integer id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El instructor con id " + id + " no existe."));
        
        if (programacionRepository.existsByInstructor(instructor)) {
            throw new IllegalStateException("No se puede eliminar el instructor porque tiene programaciones relacionadas.");
        }
        
        if (sesionRepository.existsByInstructor(instructor)) {
            throw new IllegalStateException("No se puede eliminar el instructor porque tiene sesiones relacionadas.");
        }
        
        instructorRepository.delete(instructor);
        return instructor;
    }
}

package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Instructor;
import co.edu.ufps.repositories.InstructorRepository;

@Service
public class InstructorService {
	@Autowired
	private InstructorRepository instructorRepository;

	public List<Instructor> list() {
		return instructorRepository.findAll();
	}
	
	public Instructor get(Integer id) {
		Optional<Instructor> instructorOpt = instructorRepository.findById(id);
		if (instructorOpt.isPresent()) {
			return instructorOpt.get();
		}
		return null;
	}

	public Instructor create(Instructor instructor) {
		return instructorRepository.save(instructor);
	}

	public Instructor update(Integer id, Instructor instructorDetails) {
		Optional<Instructor> instructorOpt = instructorRepository.findById(id);
		if (!instructorOpt.isPresent()) {
			return null;
		}
		Instructor instructor = instructorOpt.get();
		instructor.setNombre(instructorDetails.getNombre());
		instructor.setDocumento(instructorDetails.getDocumento());
		return instructorRepository.save(instructor);
	}

	public Instructor delete(Integer id) {
		Optional<Instructor> instructorOpt = instructorRepository.findById(id);
		if (instructorOpt.isPresent()) {
			Instructor instructor = instructorOpt.get();
			instructorRepository.delete(instructor);
			return instructor;
		}
		return null;
	}
}

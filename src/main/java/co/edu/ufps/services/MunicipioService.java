package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Municipio;
import co.edu.ufps.repositories.MunicipioRepository;

@Service
public class MunicipioService {
	@Autowired
	private MunicipioRepository municipioRepository;

	public List<Municipio> list() {
		return municipioRepository.findAll();
	}
	
	public Municipio get(Integer id) {
		Optional<Municipio> municipioOpt = municipioRepository.findById(id);
		if (municipioOpt.isPresent()) {
			return municipioOpt.get();
		}
		return null;
	}

	public Municipio create(Municipio municipio) {
		return municipioRepository.save(municipio);
	}

	public Municipio update(Integer id, Municipio municipio) {
		Optional<Municipio> municipioOpt = municipioRepository.findById(id);
		if (!municipioOpt.isPresent()) {
			return null;
		}
		Municipio updatedMunicipio = municipioOpt.get();
		updatedMunicipio.setNombre(municipio.getNombre());
		updatedMunicipio.setDane(municipio.getDane());
		return municipioRepository.save(updatedMunicipio);
	}

	public Municipio delete(Integer id) {
		Optional<Municipio> municipioOpt = municipioRepository.findById(id);
		if (municipioOpt.isPresent()) {
			Municipio municipio = municipioOpt.get();
			municipioRepository.delete(municipio);
			return municipio;
		}
		return null;
	}
}

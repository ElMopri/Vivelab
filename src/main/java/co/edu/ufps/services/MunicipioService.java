package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Municipio;
import co.edu.ufps.repositories.ColegioRepository;
import co.edu.ufps.repositories.MunicipioRepository;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private ColegioRepository colegioRepository;

    public List<Municipio> list() {
        return municipioRepository.findAll();
    }

    public Municipio get(Integer id) {
        Optional<Municipio> municipioOpt = municipioRepository.findById(id);
        if (!municipioOpt.isPresent()) {
            throw new IllegalArgumentException("El municipio con id " + id + " no existe.");
        }
        return municipioOpt.get();
    }

    public Municipio create(Municipio municipio) {
        try {
            return municipioRepository.save(municipio);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el municipio: " + e.getMessage(), e);
        }
    }

    public Municipio update(Integer id, Municipio municipio) {
        Optional<Municipio> municipioOpt = municipioRepository.findById(id);
        if (!municipioOpt.isPresent()) {
            throw new IllegalArgumentException("El municipio con id " + id + " no existe.");
        }
        Municipio updatedMunicipio = municipioOpt.get();
        updatedMunicipio.setNombre(municipio.getNombre());
        updatedMunicipio.setDane(municipio.getDane());
        try {
            return municipioRepository.save(updatedMunicipio);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el municipio: " + e.getMessage(), e);
        }
    }

    public Municipio delete(Integer id) {
        Optional<Municipio> municipioOpt = municipioRepository.findById(id);
        if (!municipioOpt.isPresent()) {
            throw new IllegalArgumentException("El municipio con id " + id + " no existe.");
        }
        Municipio municipio = municipioOpt.get();
        if (colegioRepository.existsByMunicipio(municipio)) {
            throw new IllegalStateException("No se puede eliminar el municipio porque tiene colegios relacionados.");
        }
        try {
            municipioRepository.delete(municipio);
            return municipio;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el municipio: " + e.getMessage(), e);
        }
    }
}

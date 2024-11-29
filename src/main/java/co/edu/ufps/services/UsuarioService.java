package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Instructor;
import co.edu.ufps.entities.Rol;
import co.edu.ufps.entities.Usuario;
import co.edu.ufps.repositories.InstructorRepository;
import co.edu.ufps.repositories.RolRepository;
import co.edu.ufps.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	public RolRepository rolRepository;
	@Autowired
	public InstructorRepository instructorRepository;
	
	public Usuario setInstructor(Integer usuarioId, Integer instructorId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (!usuarioOpt.isPresent()) {
            throw new IllegalArgumentException("El usuario con id " + usuarioId + " no existe.");
        } 
        Optional<Instructor> instructorOpt = instructorRepository.findById(instructorId);
        if (!instructorOpt.isPresent()) {
            throw new IllegalArgumentException("El instructor con id " + instructorId + " no existe.");
        } 
        Usuario usuario = usuarioOpt.get();
        Instructor instructor = instructorOpt.get();
        usuario.setInstructor(instructor);
        return usuarioRepository.save(usuario);
	}
	
	public Usuario addRol(Integer id, Integer rolId) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isPresent()) {
			Usuario usuario = usuarioOpt.get();
			Optional<Rol> rolOpt = rolRepository.findById(rolId);
			if (rolOpt.isPresent()) {
				usuario.addRol(rolOpt.get());
			}
			return usuarioRepository.save(usuario);
		}
		return null;
	}

	public Usuario removeRol(Integer id, Integer rolId) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isPresent()) {
			Usuario usuario = usuarioOpt.get();
			Optional<Rol> rolOpt = rolRepository.findById(rolId);
			if (rolOpt.isPresent()) {
				usuario.removeRol(rolOpt.get());
			}
			return usuarioRepository.save(usuario);
		}
		return null;
	}

    public List<Usuario> list() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de tipos de documentos: " + e.getMessage(), e);
        }
    }
    
    public Usuario get(Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get();
        }
        throw new IllegalArgumentException("El tipo de documento con id " + id + " no existe.");
    }

    public Usuario create(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error al guardar el tipo de documento: Violación de restricciones de base de datos.");
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el tipo de documento.");
        }
    }

    public Usuario update(Integer id, Usuario usuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (!usuarioOpt.isPresent()) {
            throw new IllegalArgumentException("El tipo de documento con id " + id + " no existe.");
        }
        Usuario updatedUsuario = usuarioOpt.get();
        updatedUsuario.setUsername(usuario.getUsername());
        updatedUsuario.setPassword(usuario.getPassword());
        return usuarioRepository.save(updatedUsuario);
    }

    public Usuario delete(Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (!usuarioOpt.isPresent()) {
            throw new IllegalArgumentException("El tipo de documento con id " + id + " no existe.");
        }
        Usuario usuario = usuarioOpt.get();
        usuarioRepository.delete(usuario);
        return usuario;
    }
}

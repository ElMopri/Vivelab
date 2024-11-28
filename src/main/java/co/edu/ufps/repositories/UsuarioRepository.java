package co.edu.ufps.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	//buscar un usuario mediante su nombre
		Optional<Usuario> findByUsername(String username);
		
	//verificar si un usuario existe en la base de datos
	boolean existsByUsername(String username);
}

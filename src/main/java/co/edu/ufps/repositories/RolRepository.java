package co.edu.ufps.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Rol;
import co.edu.ufps.entities.Usuario;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

	//buscar un rol mediante su nombre en la bd
	Optional<Rol> findByName(String name);
	
	
	
}

package co.edu.ufps.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import co.edu.ufps.entities.Rol;
import co.edu.ufps.entities.Usuario;
import co.edu.ufps.repositories.UsuarioRepository;

@Service
public class CustomUsersDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	
	public CustomUsersDetailsService (UsuarioRepository usuarioRep) {
		this.usuarioRepo = usuarioRep;
	}
	
	//traer una lista de autoridades por medio de una lista de roles
	public Collection<GrantedAuthority> mapToAutorities(List<Rol> roles) {
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getDescripcion())).collect(Collectors.toList());
	}
	// traer un usuario con todos sus datos por medio de su username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		return new User(usuario.getUsername(), usuario.getPassword(), mapToAutorities(usuario.getRoles()));
	}

}

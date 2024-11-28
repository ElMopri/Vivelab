package co.edu.ufps.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//validar la inforacion del token y si es exitoso se establece la autenticación de un usuario
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private CustomUsersDetailsService customUsersDetailsService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public String obtenerTokenSolicitud(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = obtenerTokenSolicitud(request);
		if(StringUtils.hasText(token) && jwtTokenProvider.validarToken(token)) {
			String username = jwtTokenProvider.obtenerUsernameDeJwt(token);
			UserDetails userDetails = customUsersDetailsService.loadUserByUsername(username);
			List<String> userRoles = userDetails.getAuthorities().stream().map(authority -> authority.getAuthority()).toList();
			if(userRoles.contains("Instructor") || userRoles.contains("Administradorr")) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, 
						userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}

}

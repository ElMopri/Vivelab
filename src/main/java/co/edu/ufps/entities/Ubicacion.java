package co.edu.ufps.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ubicacion")
public class Ubicacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String nombre;
	
	@OneToMany(mappedBy = "ubicacion_id", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Programacion> programaciones = null;
	
	@OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Sesion> sesiones;
}

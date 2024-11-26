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
@Table(name = "instructor")
public class Instructor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String nombre;
	
	@Column(length = 15)
	private String documento;
	
	@OneToMany(mappedBy = "instructor_id", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Programacion> programaciones = null;
	
	@OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Sesion> sesiones;
}

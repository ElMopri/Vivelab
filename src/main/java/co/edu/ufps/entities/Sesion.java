package co.edu.ufps.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sesion")
public class Sesion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDate fecha;
	
	private LocalTime hora;
	
	@ManyToOne
	@JoinColumn(name = "programacion_id")
	private Programacion programacion_id;
	
	@ManyToOne
	@JoinColumn(name = "instructor_id")
	private Instructor instructor_id;
	
	@ManyToOne
	@JoinColumn(name = "ubicacion_id")
	private Ubicacion ubicacion_id;
	
	@OneToMany(mappedBy = "sesion_id", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Asistente> asistentes;
	
	@OneToMany(mappedBy = "sesion_id", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Evidencia> evidencias;
}
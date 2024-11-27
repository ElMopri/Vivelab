package co.edu.ufps.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "programacion")
public class Programacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "colegio_id")
	private Colegio colegio;
	
	@ManyToOne
	@JoinColumn(name = "taller_id")
	private Taller taller;
	
	private LocalDate fecha_inicio;
	
	private LocalDate fecha_fin;
	
	private Integer cantidad;
	
	@Column(columnDefinition="TEXT")
	private String observacion;
	
	@ManyToOne
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	
	private Integer grado;
	
	@Column(length=50)
	private String grupo;
	
	@ManyToOne
	@JoinColumn(name = "ubicacion_id")
	private Ubicacion ubicacion;
	
	@OneToMany(mappedBy = "programacion", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Sesion> sesiones;
	
	@OneToMany(mappedBy = "programacion", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Inscripcion> inscripciones;
}

package co.edu.ufps.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "inscripcion", uniqueConstraints = {@UniqueConstraint(columnNames = {"participante_id", "programacion_id"})})
public class Inscripcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "participante_id")
	private Participante participante;
	
	@ManyToOne
	@JoinColumn(name = "programacion_id")
	private Programacion programacion;
	
	private LocalDate fecha;
}

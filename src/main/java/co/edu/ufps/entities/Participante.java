package co.edu.ufps.entities;

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
@Table(name = "participante")
public class Participante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "colegio_id")
	private Colegio colegio;
	
	@ManyToOne
	@JoinColumn(name = "tipo_documento_id")
	private TipoDocumento tipoDocumento;
	
	@OneToMany(mappedBy = "participante", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Asistente> asistentes;
	
	@OneToMany(mappedBy = "participante", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Inscripcion> inscripciones;
}

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
@Table(name = "colegio")
public class Colegio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "municipio_id")
	private Municipio municipio_id;
	
	@Column(length = 50)
	private String dane;
	
	@OneToMany(mappedBy = "colegio_id", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Programacion> programaciones = null;
}

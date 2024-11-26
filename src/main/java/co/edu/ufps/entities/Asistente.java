package co.edu.ufps.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "asistente")
public class Asistente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name = "participante_id")
	private Participante participante;
	
	@ManyToOne
	@JoinColumn(name = "sesion_id")
	private Sesion sesion;
}

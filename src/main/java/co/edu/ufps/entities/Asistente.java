package co.edu.ufps.entities;


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
@Table(name = "asistente", uniqueConstraints = {@UniqueConstraint(columnNames = {"sesion_id", "participante_id"})})
public class Asistente {
	
	public Asistente() {}
	
	public Asistente(Sesion sesion, Participante participante) {
		this.sesion = sesion;
		this.participante = participante;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "sesion_id")
	private Sesion sesion;
	
	@ManyToOne
	@JoinColumn(name = "participante_id")
	private Participante participante;
}

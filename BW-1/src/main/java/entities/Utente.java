package entities;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codUtente;
	private String nome;
	private String cognome;
	private String email;
	@OneToOne(mappedBy = "utente", cascade = CascadeType.ALL)
	private Abbonamento abbonamento;
	private boolean abbonamentoattivo;
	
	public Utente(String nome, String cognome, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
	}
	
	
}

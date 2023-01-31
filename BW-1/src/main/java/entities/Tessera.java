package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tessere")
@Getter
@Setter
@NoArgsConstructor
public class Tessera extends Utente{

	private LocalDate dataEmissione;
	//private LocalDate dataScadenza = dataEmissione.plusYears(1L);
	
	public Tessera(String nome, String cognome, String email, LocalDate dataEmissione) {
		super(nome, cognome, email);
		this.dataEmissione = dataEmissione;
	}
	
}

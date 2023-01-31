package entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import entities.abstracts.TitoloDiViaggio;
import enums.Periodicita;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="abbonamenti")
@Getter
@Setter
@NoArgsConstructor
public class Abbonamento extends TitoloDiViaggio{
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "codice_utente")
	private Utente utente;
	private boolean validita = true;
	@Enumerated(EnumType.STRING)
	private Periodicita durata;
	
	public Abbonamento(Utente utente, LocalDate dataEmissione, LocalDate dataScadenza, boolean validita, Periodicita durata) {
		super(dataEmissione, dataScadenza);
		this.utente = utente;
		this.validita = validita;
		this.durata = durata;
	}
	
	
}

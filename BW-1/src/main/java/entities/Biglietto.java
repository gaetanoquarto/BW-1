package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import entities.abstracts.TitoloDiViaggio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="biglietti")
@Getter
@Setter
@NoArgsConstructor
public class Biglietto extends TitoloDiViaggio{
	
	private LocalDate vidimazione;
	private boolean timbrato = false;
	
	public Biglietto(LocalDate dataEmissione, LocalDate dataScadenza, LocalDate vidimazione, boolean timbrato) {
		super(dataEmissione, dataScadenza);
		this.vidimazione = vidimazione;
		this.timbrato = timbrato;
	}
	
	
}

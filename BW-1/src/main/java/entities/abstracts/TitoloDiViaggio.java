package entities.abstracts;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="titoli_di_viaggio")
@Getter
@Setter
@NoArgsConstructor
public abstract class TitoloDiViaggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codTitoloViaggio;
	private LocalDate dataEmissione;
	private LocalDate dataScadenza;
	
	public TitoloDiViaggio(LocalDate dataEmissione, LocalDate dataScadenza) {
		this.dataEmissione = dataEmissione;
		this.dataScadenza = dataScadenza;
	}
	
	
}

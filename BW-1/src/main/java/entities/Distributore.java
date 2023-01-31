package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import entities.abstracts.Ticketing;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="distributori")
@Getter
@Setter
@NoArgsConstructor
public class Distributore extends Ticketing{
	
	private boolean inServizio;

	public Distributore(int bigliettiEmessi, String luogo, boolean inServizio) {
		super(bigliettiEmessi, luogo);
		this.inServizio = inServizio;
	}

}

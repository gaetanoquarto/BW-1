package entities.abstracts;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public abstract class Ticketing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codShop;
	private int bigliettiEmessi;
	private String luogo;
	
	public Ticketing(int bigliettiEmessi, String luogo) {
		this.bigliettiEmessi = bigliettiEmessi;
		this.luogo = luogo;
	}
	
	
	
}

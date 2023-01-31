package entities.abstracts;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import enums.TipoMezzo;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public abstract class Mezzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String targa;
	private int capienza;
	private TipoMezzo tipoMezzo;
	private boolean inServizio;
	
	public Mezzo(int capienza, TipoMezzo tipoMezzo, boolean inServizio) {
		this.capienza = capienza;
		this.tipoMezzo = tipoMezzo;
		this.inServizio = inServizio;
	}
	
	
	
}

package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import entities.abstracts.Mezzo;
import enums.TappeAutobus;
import enums.TipoMezzo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="autobus")
@Getter
@Setter
@NoArgsConstructor
public class Autobus extends Mezzo{
	private TappeAutobus capolinea;
	private TappeAutobus partenza;
	
	public Autobus(int capienza, TipoMezzo tipoMezzo, boolean inServizio, TappeAutobus capolinea,
			TappeAutobus partenza) {
		super(capienza, tipoMezzo, inServizio);
		this.capolinea = capolinea;
		this.partenza = partenza;
	}
	
	
	
	
}

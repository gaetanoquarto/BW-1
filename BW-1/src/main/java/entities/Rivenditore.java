package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import entities.abstracts.Ticketing;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="rivenditori")
@Getter
@Setter
@NoArgsConstructor
public class Rivenditore extends Ticketing{public Rivenditore(int bigliettiEmessi, String luogo) {
	
		super(bigliettiEmessi, luogo);
		
	}
}

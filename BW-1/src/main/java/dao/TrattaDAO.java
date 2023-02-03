package dao;

import java.util.Random;

import entities.Tratta;
import entities.abstracts.Mezzo;
import utils.JpaUtils;

public class TrattaDAO extends JpaUtils {

	public void save(Tratta tr) {
		
		try {
			
			t.begin();
			em.persist(tr);
			t.commit();
			
			System.out.println( "Tratta inserita correttamente" );
		} catch(Exception e) {
			System.out.println( "ERRORE durante l'inserimento della tratta!!" );
		}
		
	}
	
	public static void tempoEffettivo(int id) {
		Mezzo m = em.find(Mezzo.class, id);
		
		int number = m.getTratta().getTempoPercorrenza();
	    int range = 5;
	    Random rand = new Random();
	    int randomNumber = rand.nextInt(range * 2 + 1) - range;
	    int result = number + randomNumber;
		
		System.out.println("Hai effettuato la corsa " + m.getTratta().getPartenza() + " - " + m.getTratta().getCapolinea() + " in " + result + " minuti.");
		
	}
	
}

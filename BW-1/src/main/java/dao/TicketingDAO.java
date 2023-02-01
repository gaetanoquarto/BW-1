package dao;

import entities.Utente;
import entities.abstracts.Ticketing;
import utils.JpaUtils;

public class TicketingDAO extends JpaUtils {

	public void save(Ticketing tick) {
		
		try {
			
			t.begin();
			em.persist(tick);
			t.commit();
			
			System.out.println( "Elemento inserito correttamente" );
		} catch(Exception e) {
			System.out.println( "ERRORE durante l'inserimento dell'elemento!!" );
		}
		
	}
	
	public static void countBiglietti(int id) {
		Ticketing ti = em.find(Ticketing.class, id);
		
		int counter = ti.getCounterBiglietti() - 1;
		int counter2 = ti.getBigliettiEmessi() + 1;
		
		try {
			ti.setCounterBiglietti(counter);
			ti.setBigliettiEmessi(counter2);
			
			t.begin();
			em.persist(ti);
			t.commit();
			
			
		} catch(Exception e1) {
			System.out.println("Errore!");
		}
	}
	public static void countAbbonamenti(int id) {
		Ticketing ti = em.find(Ticketing.class, id);
		
		int counter = ti.getAbbonamentiEmessi() + 1;
		
		try {
			ti.setAbbonamentiEmessi(counter);
			
			t.begin();
			em.persist(ti);
			t.commit();
			
			
		} catch(Exception e1) {
			System.out.println("Errore!");
		}
	}
	
	public static int getTicketNumber(int id) {
		Ticketing ti = em.find(Ticketing.class, id);
		
		return ti.getCounterBiglietti();
	}
	
	
	public static void getTitoliEmessi(int id) {
		Ticketing ti = em.find(Ticketing.class, id);
		
		int bigliettiEmessi = ti.getBigliettiEmessi();
		int abbonamentiEmessi = ti.getAbbonamentiEmessi();
		String stazione = ti.getLuogo();
		
		System.out.println("Sono stati emessi " + bigliettiEmessi + " biglietti e " + abbonamentiEmessi + " abbonamenti nella " + stazione);
	}
	
}

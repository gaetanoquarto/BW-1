package dao;

import entities.Utente;
import utils.JpaUtils;

public class UtenteDAO extends JpaUtils{
	public void save(Utente u) {

		try {

			t.begin();
			em.persist(u);
			t.commit();

			System.out.println("Elemento inserito correttamente!");
		} catch (Exception e) {
			System.out.println("Errore nell'inserimento dell'Elemento");
		}

	}
	
	public Utente getUtenteById(long id) {
		Utente e = em.find(Utente.class, id);
		
		if( e == null ) {
			System.out.println( "Il numero di tessera " + id + " non esiste!" );
		}
		
		return e;
	}
	
	public static void updateUtenteById(long id) {
		Utente u = em.find(Utente.class, id);
		
		if(u == null) {
			System.out.println("Errore.");
			return;
		}
		
		try {
			u.setAbbonamentoattivo(true);
			
			t.begin();
			em.persist(u);
			t.commit();
			
			
		} catch(Exception e1) {
			System.out.println("Errore.");
		}
		
	}
}
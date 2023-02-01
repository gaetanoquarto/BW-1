package dao;

import java.time.LocalDate;

import javax.persistence.Query;

import entities.Abbonamento;
import entities.Biglietto;
import entities.Tessera;
import entities.abstracts.TitoloDiViaggio;
import utils.JpaUtils;

public class TitoloDiViaggioDAO extends JpaUtils {

	public void save(TitoloDiViaggio tit) {
		
		try {
			
			t.begin();
			em.persist(tit);
			t.commit();
			
			System.out.println( "Ticket inserito correttamente" );
		} catch(Exception e) {
			System.out.println( "ERRORE durante l'inserimento del ticket!!" + e );
		}
		
	}
	
	public static void checkTessera(long id) {
		Tessera t = em.find(Tessera.class, id);
		
		if( t == null ) {
			System.out.println( "La tessera numero " + id + " non è stata trovata!" );
			System.exit(0);
		}
		
		boolean validita = t.isValidita();
		
		if(validita == false) {
			System.out.println("ERROR! La tessera è scaduta, impossibile creare l'abbonamento!");
			System.exit(0);
		} 
	}
	
	public static void conteggioTitoli(Class<?> classe, LocalDate startDate, LocalDate endDate) {
	    Query q = em.createQuery("SELECT COUNT(*) FROM " + classe.getName() + " WHERE dataEmissione BETWEEN :start_date AND :end_date");
	    q.setParameter("start_date", startDate);
	    q.setParameter("end_date", endDate);

	    String results = q.getSingleResult().toString();

	    System.out.println("Il numero di " + classe.getSimpleName() + " emessi tra il " + startDate + " e il " + endDate + " sono: " + results);
	}
}

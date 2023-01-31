package dao;

import entities.abstracts.Ticketing;
import utils.JpaUtils;

public class TicketingDAO extends JpaUtils {
	public void save(Ticketing tick) {

		try {

			t.begin();
			em.persist(tick);
			t.commit();

			System.out.println("Elemento inserito correttamente!");
		} catch (Exception e) {
			System.out.println("Errore nell'inserimento dell'Elemento");
		}

	}
}

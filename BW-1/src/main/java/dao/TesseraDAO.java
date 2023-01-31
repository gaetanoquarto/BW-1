package dao;

import ch.qos.logback.classic.Logger;
import entities.Tessera;
import utils.JpaUtils;

public class TesseraDAO extends JpaUtils{
	public void save(Tessera tess) {

		try {

			t.begin();
			em.persist(tess);
			t.commit();

			System.out.println("Elemento inserito correttamente!");
		} catch (Exception e) {
			System.out.println("Errore nell'inserimento dell'Elemento");
		}

	}

}

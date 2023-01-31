package dao;

import entities.abstracts.Mezzo;
import utils.JpaUtils;

public class MezzoDAO extends JpaUtils{
	public void save(Mezzo m) {

		try {

			t.begin();
			em.persist(m);
			t.commit();

			System.out.println("Elemento inserito correttamente!");
		} catch (Exception e) {
			System.out.println("Errore nell'inserimento dell'Elemento");
		}

	}

}

package dao;

import entities.abstracts.TitoloDiViaggio;
import utils.JpaUtils;

public class TitoloDiViaggioDAO extends JpaUtils{
	public void save(TitoloDiViaggio tit) {

		try {

			t.begin();
			em.persist(tit);
			t.commit();

			System.out.println("Elemento inserito correttamente!");
		} catch (Exception e) {
			System.out.println("Errore nell'inserimento dell'Elemento" + e);
		}

	}
}

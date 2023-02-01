package app;

import java.time.LocalDate;

import dao.TesseraDAO;
import dao.TicketingDAO;
import dao.TitoloDiViaggioDAO;
import entities.Abbonamento;
import entities.Biglietto;
import entities.abstracts.TitoloDiViaggio;
import utils.Gestionale;
import utils.Menu;

public class App {

	public static void main(String[] args) {

		//Gestionale.counter();
		//Gestionale.addMezzi();
		
		Menu.runApp();
		//TitoloDiViaggioDAO.conteggioTitoli(Abbonamento.class, LocalDate.now(), LocalDate.now().plusDays(30));
		//TicketingDAO.getTitoliEmessi(1);
		//Menu.saveDistributore();
		//Menu.saveRivenditore();
		
	}
	
}

package app;

import java.time.LocalDate;

import dao.MezzoDAO;
import dao.TesseraDAO;
import dao.TicketingDAO;
import dao.TitoloDiViaggioDAO;
import entities.Abbonamento;
import entities.Biglietto;
import utils.Gestionale;
import utils.Menu;

public class App {

	public static void main(String[] args) {
		
		
		//Gestionale.counter();
		Menu.runApp();
		
		//TitoloDiViaggioDAO.conteggioTitoli(Biglietto.class, LocalDate.now(), LocalDate.now().plusDays(30));
		//TicketingDAO.getTitoliEmessi(1);
		//MezzoDAO.conteggioBigliettiVidimati(LocalDate.now(), LocalDate.now().plusDays(30));
				

		
		
		
		
		
		//Gestionale.addMezzi();
		
		//Menu.saveDistributore();
		//Menu.saveRivenditore();

		
	}
	
}

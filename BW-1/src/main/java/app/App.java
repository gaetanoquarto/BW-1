package app;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.MezzoDAO;
import dao.TesseraDAO;
import dao.TicketingDAO;
import dao.TitoloDiViaggioDAO;
import dao.UtenteDAO;
import entities.Abbonamento;
import entities.Autobus;
import entities.Biglietto;
import entities.Distributore;
import entities.Rivenditore;
import entities.Tessera;
import entities.Tram;
import entities.Utente;
import enums.Periodicita;
import enums.TappeAutobus;
import enums.TappeTram;
import enums.TipoMezzo;
import utils.JpaUtils;

public class App {

	static Scanner sc = new Scanner(System.in);
	static final Logger logger = LoggerFactory.getLogger(JpaUtils.class);

	public static void main(String[] args) {
		runApp();
		// saveInDB();
	}

	public static void runApp() {

		try {
			System.out.println("-------------Benvenuto a Roma--------------");
			System.out.println("-------------------------------------------");
			System.out.println("1 - Stazione Termini");
			System.out.println("2 - Stazione Tiburtina");
			System.out.println("------ Da che stazione vuoi partire? ------");
			int selezione = sc.nextInt();
			sc.nextLine();

			switch (selezione) {
			case (1):
				System.out.println("Hai scelto Stazione Termini");
				break;
			case (2):
				System.out.println("Hai scelto Stazione Tiburtina");
				break;
			default:
				logger.error("Valore non presente nella lista!");
			}

			System.out.println("1 - Acquista tessera");
			System.out.println("2 - Acquista titolo di viaggio");
			System.out.println("3 - Sali a bordo di:");
			System.out.println("------ Da che stazione vuoi partire? ------");
			int selezione2 = sc.nextInt();
			sc.nextLine();
			switch (selezione2) {
			case (1):
				acquistaTessera();
				break;
			case (2):
				acquistaTitoloDiViaggio();
				break;
			case (3):
				System.out.println("Hai scelto Stazione Tiburtina");
				break;
			default:
				logger.error("Valore non presente nella lista!");
			}

		} catch (InputMismatchException e) {
			logger.error("Inserisci un valore corretto");
		}
		/*
		 * System.out.println("-------------------------------------------");
		 * System.out.println("Vuoi continuare? (S/N)"); String input = sc.nextLine();
		 * attivo = input.equalsIgnoreCase("S");
		 */

	}

	public static void acquistaTessera() {
		System.out.println("Inserisci il tuo nome");
		String nome = sc.nextLine();

		System.out.println("Inserisci il tuo cognome");
		String cognome = sc.nextLine();

		System.out.println("Inserisci la tua email");
		String email = sc.nextLine();

		saveTessera(nome, cognome, email, LocalDate.now());

	}

	public static void acquistaTitoloDiViaggio() {
		System.out.println("1 - Biglietto ordinario");
		System.out.println("2 - Abbonamento");
		System.out.println("Scegli cosa comprare e muoviti----------------");
		int selezione3 = sc.nextInt();

		switch (selezione3) {
		case (1):
			saveBiglietto();
			break;
		case (2):
			acquistaAbbonamento();
			break;
		default:
			logger.error("Valore non presente nella lista!");
		}
	}

	public static void scegliMezzo() {

	}

	public static Autobus saveAutobus() {
		Autobus aut = new Autobus(50, TipoMezzo.AUTOBUS, true, TappeAutobus.E, TappeAutobus.H);

		MezzoDAO mezzoDAO = new MezzoDAO();
		mezzoDAO.save(aut);
		return aut;
	}

	public static Tram saveTram() {
		Tram t = new Tram(50, TipoMezzo.TRAM, true, TappeTram.A, TappeTram.D);

		MezzoDAO mezzoDAO = new MezzoDAO();
		mezzoDAO.save(t);
		return t;
	}

	public static Biglietto saveBiglietto() {
		Biglietto b = new Biglietto(LocalDate.parse("2023-02-08"), LocalDate.parse("2023-02-10"),
				LocalDate.parse("2023-02-09"), true);

		TitoloDiViaggioDAO titoloDiViaggioDAO = new TitoloDiViaggioDAO();
		titoloDiViaggioDAO.save(b);
		return b;
	}
	public static void acquistaAbbonamento() {
		System.out.println("Inserisci il tuo numero tessera");
		long nTessera = sc.nextLong();
		
		System.out.println("1 - Settimanale");
		System.out.println("2 - Mensile");
		int periodo = sc.nextInt();

		switch (periodo) {
		case (1):
			saveAbbonamento(getUtente(nTessera), LocalDate.now(), LocalDate.now().plusYears(1), true, Periodicita.SETTIMANALE);
			updateUtente(nTessera);
			break;
		case (2):
			saveAbbonamento(getUtente(nTessera), LocalDate.now(), LocalDate.now().plusYears(1), true, Periodicita.MENSILE);
		updateUtente(nTessera);
			break;
		default:
			logger.error("Valore non presente nella lista!");
		}

	}
	
	public static Abbonamento saveAbbonamento(Utente codice_utente, LocalDate dataEmissione, LocalDate dataScadenza, boolean attivo,
			Periodicita periodicita) {
		Abbonamento a = new Abbonamento(codice_utente, dataEmissione, dataScadenza, attivo, periodicita);
		

		TitoloDiViaggioDAO titoloDiViaggioDAO = new TitoloDiViaggioDAO();
		titoloDiViaggioDAO.save(a);
		return a;
	}
	
	

	public static Distributore saveDistributore() {
		Distributore d = new Distributore(100, "Roma", true);

		TicketingDAO ticketingDAO = new TicketingDAO();
		ticketingDAO.save(d);
		return d;
	}

	public static Rivenditore saveRivenditore() {
		Rivenditore r = new Rivenditore(100, "Bologna");

		TicketingDAO ticketingDAO = new TicketingDAO();
		ticketingDAO.save(r);
		return r;
	}

	public static Utente saveUtente() {
		Utente u = new Utente("Padre", "Figlio", "spirito@santo.amen");

		UtenteDAO utenteDAO = new UtenteDAO();
		utenteDAO.save(u);
		return u;
	}
	
	public static void updateUtente(long nTessera) {		
		UtenteDAO.updateUtenteById(nTessera);
	}
	
	public static Utente getUtente(long id) {
		UtenteDAO utente = new UtenteDAO();
		return utente.getUtenteById(id);
	}

	public static Tessera saveTessera(String nome, String cognome, String email, LocalDate dataEmissione) {
		Tessera u = new Tessera(nome, cognome, email, dataEmissione);

		TesseraDAO tesseraDAO = new TesseraDAO();
		tesseraDAO.save(u);
		return u;
	}
}

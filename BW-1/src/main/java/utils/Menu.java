package utils;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
import entities.abstracts.Mezzo;
import enums.Periodicita;

public class Menu extends JpaUtils {
	
	static Scanner sc = new Scanner(System.in);
	static final Logger logger = LoggerFactory.getLogger(JpaUtils.class);
	static int selezione;
	static final String ANSI_RESET = "\u001B[0m";
	static final String ANSI_RED = "\u001B[31m";
	static final String ANSI_GREEN = "\033[0;32m";
	static final String ANSI_YELLOW = "\033[1;33m";
	static final String ANSI_BACKGROUND = "\u001B[41m";
	//static boolean attivo = true;
	static long nTessera;
	
	public static void runApp() {
		TesseraDAO.update();
		
		
		//do {
			try {
				System.out.println("Benvenuto a Roma!");
			    System.out.println(ANSI_RED + "-------------------------------------------" + ANSI_RESET);
				System.out.println("1 - Stazione Tiburtina");
				System.out.println("2 - Stazione Termini");
				System.out.println(ANSI_GREEN + "Da che stazione vuoi partire? <---" + ANSI_RESET);
				selezione = sc.nextInt();

				switch (selezione) {
				case (1):
					System.out.println("Hai scelto Stazione Tiburtina");
					System.out.println("Benvenuto dal rivenditore");
					break;
				case (2):
					System.out.println("Hai scelto Stazione Termini");
					System.out.println("Benvenuto nel distributore");
					break;
				default:
					logger.error("Valore non presente nella lista!");
					break;
				}

				System.out.println("1 - Gestione tessera");
				System.out.println("2 - Acquista titolo di viaggio");
				System.out.println("3 - Sali a bordo di:");
				System.out.println(ANSI_GREEN + "Cosa vuoi fare? <---" + ANSI_RESET);
				int selezione2 = sc.nextInt();
				sc.nextLine();
				switch (selezione2) {
				case (1):
					gestioneTessera();
					break;
				case (2):
					acquistaTitoloDiViaggio();
					break;
				case (3):
					scegliMezzo();
					break;
				default:
					logger.error("Valore non presente nella lista!");
					break;
				}

			} catch (InputMismatchException e) {
				logger.error("Inserisci un valore corretto");
			}
			
//			System.out.println(ANSI_RED + "-------------------------------------------" + ANSI_RESET);
//			System.out.println("Vuoi continuare? (S/N)"); 
//			String input = sc.nextLine();
//			attivo = input.equalsIgnoreCase("S");
			
		//} while(attivo);
		

	}
	
	public static void acquistaTessera() {
		sc.nextLine();
		System.out.println("Inserisci il tuo nome");
		String nome = sc.nextLine();
		
		System.out.println("Inserisci il tuo cognome");
		String cognome = sc.nextLine();

		System.out.println("Inserisci la tua email");
		String email = sc.nextLine();

		saveTessera(nome, cognome, email, LocalDate.now(), LocalDate.now().plusYears(1));

	}

	public static void acquistaTitoloDiViaggio() {
		System.out.println("1 - Biglietto ordinario (" + TicketingDAO.getTicketNumber(selezione) + " biglietti rimanenti)");
		System.out.println("2 - Abbonamento");
		System.out.println(ANSI_GREEN + "Scegli cosa comprare <---" + ANSI_RESET);
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
			break;
		}
	}

	public static void scegliMezzo() {	
		
		List<Autobus> aut;
		List<Tram> tr;
		
		if(selezione == 1) {
			
			Query queryAutobus = em.createQuery(
					"SELECT a FROM Autobus a WHERE a.partenza = 'STAZIONE_TIBURTINA'"
			);
			Query queryTram = em.createQuery(
					"SELECT t FROM Tram t WHERE t.partenza = 'STAZIONE_TIBURTINA'"
			);
			
			aut = queryAutobus.getResultList();			
			tr = queryTram.getResultList();		
			
			for(int i = 0; i < aut.size(); i++) {
				Autobus a = aut.get(i);
				System.out.println("A" + (i+1) + " - " + a.getClass().getSimpleName() + " " + a.getNumero() + ANSI_YELLOW + " direzione --> " + ANSI_RESET);
			}
			for(int i = 0; i < tr.size(); i++) {
				Tram t = tr.get(i);
				System.out.println("T" + (i+1) + " - " + t.getClass().getSimpleName() + " " + t.getNumero() + ANSI_YELLOW + " direzione --> " + ANSI_RESET);
			}
			
		} else if(selezione == 2) {
			
			Query queryAutobus = em.createQuery(
					"SELECT a FROM Autobus a WHERE a.partenza = 'STAZIONE_TERMINI'"
				);
			Query queryTram = em.createQuery(
					"SELECT t FROM Tram t WHERE t.partenza = 'STAZIONE_TERMINI'"
			);
			
			aut = queryAutobus.getResultList();			
			tr = queryTram.getResultList();	
			
			for(int i = 0; i < aut.size(); i++) {
				Autobus a = aut.get(i);
				System.out.println("A" + (i+1) + " - " + a.getClass().getSimpleName() + " " + a.getNumero() + ANSI_YELLOW + " direzione --> " + ANSI_RESET);
			}
			for(int i = 0; i < tr.size(); i++) {
				Tram t = tr.get(i);
				System.out.println("T" + (i+1) + " - " + t.getClass().getSimpleName() + " " + t.getNumero() + ANSI_YELLOW + " direzione --> " + ANSI_RESET);
			}
			
		}
		
		System.out.println(ANSI_GREEN + "Scegli su che mezzo viaggiare <---" + ANSI_RESET);
	}

	public static void saveBiglietto() {
		Biglietto b = new Biglietto();
		b.setDataEmissione(LocalDate.now());
		b.setDataScadenza(LocalDate.now().plusDays(1));

		TitoloDiViaggioDAO titoloDiViaggioDAO = new TitoloDiViaggioDAO();
		titoloDiViaggioDAO.save(b);
		TicketingDAO.countBiglietti(selezione);
	}

	public static void acquistaAbbonamento() {
		System.out.println("Inserisci il tuo numero tessera");
		nTessera = sc.nextLong();
		TitoloDiViaggioDAO titoloDAO = new TitoloDiViaggioDAO();
		titoloDAO.checkTessera(nTessera);
		
		System.out.println("1 - Settimanale");
		System.out.println("2 - Mensile");
		int periodo = sc.nextInt();

		switch (periodo) {
		case (1):
			saveAbbonamento(getUtente(nTessera), LocalDate.now(), LocalDate.now().plusWeeks(1), true, Periodicita.SETTIMANALE);
			break;
		case (2):
			saveAbbonamento(getUtente(nTessera), LocalDate.now(), LocalDate.now().plusMonths(1), true, Periodicita.MENSILE);
			break;
		default:
			logger.error("Valore non presente nella lista!");
			break;
		}


	}
	
	public static void saveAbbonamento(Utente codice_utente, LocalDate dataEmissione, LocalDate dataScadenza, boolean attivo,
			Periodicita periodicita) {
		Abbonamento a = new Abbonamento(codice_utente, dataEmissione, dataScadenza, attivo, periodicita);
		

		TitoloDiViaggioDAO titoloDiViaggioDAO = new TitoloDiViaggioDAO();
		titoloDiViaggioDAO.save(a);
		
		updateUtente(nTessera, a);
		TicketingDAO.countAbbonamenti(selezione);


	}
	
	public static Distributore saveDistributore() {
		Distributore d = new Distributore();
		d.setCounterBiglietti(100);
		d.setInServizio(true);
		d.setLuogo("Stazione Tiburtina");

		TicketingDAO ticketingDAO = new TicketingDAO();
		ticketingDAO.save(d);
		return d;
	}

	public static Rivenditore saveRivenditore() {
		Rivenditore r = new Rivenditore();
		r.setCounterBiglietti(50);
		r.setLuogo("Stazione Termini");

		TicketingDAO ticketingDAO = new TicketingDAO();
		ticketingDAO.save(r);
		return r;
	}

	public static Utente saveUtente() {
		Utente u = new Utente("Mario", "Rossi", "mario.rossi@gmail.com");

		UtenteDAO utenteDAO = new UtenteDAO();
		utenteDAO.save(u);
		return u;
	}
	
	public static void updateUtente(long nTessera, Abbonamento abbonamento) {		
		UtenteDAO.updateUtenteById(nTessera, abbonamento);
	}
	
	public static Utente getUtente(long id) {
		UtenteDAO utente = new UtenteDAO();
		return utente.getUtenteById(id);
	}

	public static Tessera saveTessera(String nome, String cognome, String email, LocalDate dataEmissione, LocalDate dataScadenza) {
		Tessera u = new Tessera(nome, cognome, email, dataEmissione, dataScadenza);

		TesseraDAO tesseraDAO = new TesseraDAO();
		tesseraDAO.save(u);
		return u;
	}
	
	public static void gestioneTessera() {
		System.out.println(ANSI_GREEN + "Benvenuto nella gestione tessera <---" + ANSI_RESET);
		System.out.println("1 - Crea tessera");
		System.out.println("2 - Verifica dati tessera");
		System.out.println("3 - Rinnova tessera");
		int selezione4 = sc.nextInt();
		switch(selezione4) {
			case(1):
				acquistaTessera();
				break;
			case(2):
				verificaTessera();
				break;
			case(3):
				rinnovaTessera();
				break;
		}
	}
	
	public static void verificaTessera() {
		System.out.println("Inserisci numero tessera");
		long selezione5 = sc.nextLong();
		
		TesseraDAO tesseraDAO = new TesseraDAO();
		tesseraDAO.getDatiTessera(selezione5);
	}
	
	public static void rinnovaTessera() {
		System.out.println("Inserisci numero tessera da rinnovare");
		long selezione6 = sc.nextLong();
		
		TesseraDAO tesseraDAO = new TesseraDAO();
		tesseraDAO.rinnovaTessera(selezione6);
	}
	
}
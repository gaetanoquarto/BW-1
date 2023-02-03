package utils;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.MezzoDAO;
import dao.TesseraDAO;
import dao.TicketingDAO;
import dao.TitoloDiViaggioDAO;
import dao.TrattaDAO;
import dao.UtenteDAO;
import entities.Abbonamento;
import entities.Autobus;
import entities.Biglietto;
import entities.Distributore;
import entities.Rivenditore;
import entities.Tessera;
import entities.Tram;
import entities.Tratta;
import entities.Utente;
import entities.abstracts.Mezzo;
import entities.abstracts.Ticketing;
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
	static long nTessera;

	static int mezzoId;
	static Mezzo v;
	static int selectMezzo;

	public static void runApp() {
		

		boolean attivo;
		do {
			TesseraDAO.update();
			MezzoDAO.queryManutenzione();
			TitoloDiViaggioDAO.updateAbbonamento();
			try {
				System.out.println("Benvenuto a Roma!");
				System.out.println(ANSI_RED + "-------------------------------------------" + ANSI_RESET);
				System.out.println("1 - Stazione Tiburtina");
				System.out.println("2 - Stazione Termini");
				System.out.println(ANSI_GREEN + "Da che stazione vuoi partire? <---" + ANSI_RESET);
				selezione = sc.nextInt();

				switch (selezione) {
				case (1):
					TicketingDAO.checkDistributore(1);
					break;
				case (2):
					System.out.println("Hai scelto Stazione Termini");
					System.out.println("Benvenuto dal rivenditore");
					break;
				default:
					logger.error("Valore non presente nella lista!");
					System.exit(0);
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
					System.exit(0);
					break;
				}

			} catch (InputMismatchException e) {
				logger.error("Inserisci un valore corretto");
			}

			System.out.println(ANSI_RED + "-------------------------------------------" + ANSI_RESET);
			System.out.println("Vuoi continuare? (S/N)");
			sc.nextLine();
			String input = sc.nextLine();
			attivo = input.equalsIgnoreCase("S");

		} while (attivo);

	}

	public static void acquistaTessera() {
		System.out.println("Inserisci il tuo nome");
		String nome = sc.next();


		System.out.println("Inserisci il tuo cognome");
		String cognome = sc.next();

		System.out.println("Inserisci la tua email");
		String email = sc.next();

		saveTessera(nome, cognome, email, LocalDate.now(), LocalDate.now().plusYears(1));

	}

	public static void acquistaTitoloDiViaggio() {
		System.out.println(
				"1 - Biglietto ordinario (" + TicketingDAO.getTicketNumber(selezione) + " biglietti rimanenti)");
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

		if (selezione == 1) {

			String jpql = "SELECT m FROM Mezzo m JOIN m.tratta t WHERE t.partenza = :partenza AND m.inServizio = true";

			TypedQuery<Mezzo> query = em.createQuery(jpql, Mezzo.class);
			query.setParameter("partenza", "Stazione Tiburtina");

			List<Mezzo> mezzi = query.getResultList();

			System.out.println("Mezzi in servizio: ");
			for (int i = 0; i < mezzi.size(); i++) {
				v = mezzi.get(i);
				mezzoId = v.getMezzo_id();
				System.out.println(mezzoId + " " + v);
			}

			System.out.println(ANSI_GREEN + "Scegli su che mezzo viaggiare <---" + ANSI_RESET);
			selectMezzo = sc.nextInt();

			boolean mezzoEsistente = false;
			for (int i = 0; i < mezzi.size(); i++) {
				v = mezzi.get(i);
				mezzoId = v.getMezzo_id();
				if (selectMezzo == mezzoId) {
					mezzoEsistente = true;
					break;
				}
			}

			if (mezzoEsistente) {
				Mezzo m = em.find(Mezzo.class, selectMezzo);
				System.out.println("Sei salito su " + m.getClass().getSimpleName().toUpperCase() + " " + m.getNumero());
				convalidaTicket();
				TrattaDAO.tempoEffettivo(selectMezzo);
			} else {
				System.out.println("mezzo non esistente.");
			}

		} else if (selezione == 2) {

			String jpql = "SELECT m FROM Mezzo m JOIN m.tratta t WHERE t.partenza = :partenza  AND m.inServizio = true";

			TypedQuery<Mezzo> query = em.createQuery(jpql, Mezzo.class);
			query.setParameter("partenza", "Stazione Termini");

			List<Mezzo> mezzi = query.getResultList();

			System.out.println("Mezzi in servizio: ");
			for (int i = 0; i < mezzi.size(); i++) {
				v = mezzi.get(i);
				mezzoId = v.getMezzo_id();
				System.out.println(mezzoId + " " + v);
			}

			System.out.println(ANSI_GREEN + "Scegli su che mezzo viaggiare <---" + ANSI_RESET);
			selectMezzo = sc.nextInt();

			boolean mezzoEsistente = false;
			for (int i = 0; i < mezzi.size(); i++) {
				v = mezzi.get(i);
				mezzoId = v.getMezzo_id();
				if (selectMezzo == mezzoId) {
					mezzoEsistente = true;
					break;
				}
			}

			if (mezzoEsistente) {
				Mezzo m = em.find(Mezzo.class, selectMezzo);
				System.out.println("Sei salito su " + m.getClass().getSimpleName().toUpperCase() + " " + m.getNumero());
				convalidaTicket();
				TrattaDAO.tempoEffettivo(selectMezzo);
			} else {
				System.out.println("mezzo non esistente.");
			}

		}

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
		creaAbbonamento();
	}

	public static void saveAbbonamento(Utente codice_utente, LocalDate dataEmissione, LocalDate dataScadenza,
			boolean attivo, Periodicita periodicita) {
		Abbonamento a = new Abbonamento(codice_utente, dataEmissione, dataScadenza, attivo, periodicita);

		TitoloDiViaggioDAO titoloDiViaggioDAO = new TitoloDiViaggioDAO();
		titoloDiViaggioDAO.save(a);

		updateUtente(nTessera, a);
		TicketingDAO.countAbbonamenti(selezione);

	}

	public static void creaAbbonamento() {
		Utente u = em.find(Utente.class, nTessera);
		if (u.getAbbonamento() != null) {
			boolean validita = u.getAbbonamento().isValidita();
			if (validita == true) {
				System.out.println("Hai già un abbonamento attivo, impossibile crearne un altro!");
				System.exit(0);
			} else {
				int id = u.getAbbonamento().getTitolo_id();

				System.out.println("Rinnova Abbonamento: ");
				System.out.println("1 - Settimanale");
				System.out.println("2 - Mensile");
				int periodo = sc.nextInt();

				switch (periodo) {
				case (1):
					Abbonamento a = em.find(Abbonamento.class, id);
					a.setDataEmissione(LocalDate.now());
					a.setDataScadenza(LocalDate.now().plusWeeks(1));
					a.setValidita(true);
					a.setDurata(Periodicita.SETTIMANALE);
					t.begin();
					em.persist(a);
					t.commit();
					updateUtente(nTessera, a);
					break;
				case (2):
					Abbonamento ab = em.find(Abbonamento.class, id);
					ab.setDataEmissione(LocalDate.now());
					ab.setDataScadenza(LocalDate.now().plusMonths(1));
					ab.setValidita(true);
					ab.setDurata(Periodicita.MENSILE);
					t.begin();
					em.persist(ab);
					t.commit();
					updateUtente(nTessera, ab);
					break;
				default:
					logger.error("Valore non presente nella lista!");
					break;
				}
				System.out.println("Abbonamento rinnovato!");

			}
		} else if (u.getAbbonamento() == null) {
			System.out.println("1 - Settimanale");
			System.out.println("2 - Mensile");
			int periodo = sc.nextInt();

			switch (periodo) {
			case (1):
				saveAbbonamento(getUtente(nTessera), LocalDate.now(), LocalDate.now().plusWeeks(1), true,
						Periodicita.SETTIMANALE);
				break;
			case (2):
				saveAbbonamento(getUtente(nTessera), LocalDate.now(), LocalDate.now().plusMonths(1), true,
						Periodicita.MENSILE);
				break;
			default:
				logger.error("Valore non presente nella lista!");
				break;
			}
		}
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

	public static Tessera saveTessera(String nome, String cognome, String email, LocalDate dataEmissione,
			LocalDate dataScadenza) {
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
		switch (selezione4) {
		case (1):
			acquistaTessera();
			break;
		case (2):
			verificaTessera();
			break;
		case (3):
			rinnovaTessera();
			break;
		default:
			logger.error("Valore errato! Inserisci un valore corretto.");
			break;
		}
	}

	public static void convalidaTicket() {
		System.out.println("Inserisci il codice biglietto/abbonamento");
		int select = sc.nextInt();

		TitoloDiViaggioDAO titoloDAO = new TitoloDiViaggioDAO();
		titoloDAO.getTitolo(select, selectMezzo);
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
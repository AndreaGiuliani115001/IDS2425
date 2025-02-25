package it.unicam.IDS2425.factory;

import it.unicam.IDS2425.model.Azienda;
import it.unicam.IDS2425.model.Utente;
import it.unicam.IDS2425.model.Ruolo;

/**
 * Interfaccia per la creazione di utenti.
 * Definisce il metodo per la creazione di un utente con i parametri necessari.
 */
public interface UtenteFactory {

    /**
     * Crea un nuovo utente.
     *
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param email L'email dell'utente.
     * @param password La password dell'utente.
     * @param ruolo Il ruolo assegnato all'utente.
     * @param azienda L'azienda a cui l'utente è associato.
     * @return L'istanza dell'utente creato.
     */
    Utente creaUtente(String nome, String cognome, String email, String password, Ruolo ruolo, Azienda azienda);
}
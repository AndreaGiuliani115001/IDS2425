package it.unicam.IDS2425.factory;

import it.unicam.IDS2425.model.*;
import org.springframework.stereotype.Component;

/**
 * Implementazione concreta della factory per la creazione di utenti.
 * Registra il bean come componente Spring e fornisce la logica per la creazione di utenti
 * in base al loro ruolo specifico.
 */
@Component
public class UtenteFactoryConcreto implements UtenteFactory {

    /**
     * Crea un nuovo utente in base al ruolo specificato.
     *
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param email L'email dell'utente.
     * @param password La password dell'utente.
     * @param ruolo Il ruolo assegnato all'utente.
     * @param azienda L'azienda a cui l'utente è associato.
     * @return Un'istanza di Utente corrispondente al ruolo specificato.
     * @throws IllegalArgumentException Se il ruolo non è valido.
     */
    @Override
    public Utente creaUtente(String nome, String cognome, String email, String password, Ruolo ruolo, Azienda azienda) {
        switch (ruolo) {
            case PRODUTTORE:
                return new Produttore(nome, cognome, email, password, azienda);
            case TRASFORMATORE:
                return new Trasformatore(nome, cognome, email, password, azienda);
            case DISTRIBUTORE:
                return new Distributore(nome, cognome, email, password, azienda);
            case CURATORE:
                return new Curatore(nome, cognome, email, password, azienda);
            case GESTORE:
                return new Gestore(nome, cognome, email, password, azienda);
            default:
                throw new IllegalArgumentException("Ruolo non valido");
        }
    }
}
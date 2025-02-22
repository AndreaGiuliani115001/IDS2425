package it.unicam.IDS2425.service.factory;

import it.unicam.IDS2425.model.*;
import org.springframework.stereotype.Component;

@Component  // <-- AGGIUNTO PER REGISTRARE COME BEAN
public class UtenteFactoryConcreto implements UtenteFactory {

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

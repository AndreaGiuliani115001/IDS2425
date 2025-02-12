package it.unicam.IDS2425.service.factory;

import it.unicam.IDS2425.model.*;
import org.springframework.stereotype.Component;

@Component
public class UtenteFactoryConcreto implements UtenteFactory {

    @Override
    public Utente creaUtente(String nome, String cognome, String email, String password, Ruolo ruolo) {
        switch (ruolo) {
            case PRODUTTORE:
                return new Produttore(nome, cognome, email, password);
            case CURATORE:
                return new Curatore(nome, cognome, email, password);
            case ANIMATORE:
                return new Animatore(nome, cognome, email, password);
            default:
                throw new IllegalArgumentException("Tipo di utente non supportato: " + ruolo);
        }
    }
}

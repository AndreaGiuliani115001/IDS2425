package it.unicam.IDS2425.service.factory;

import it.unicam.IDS2425.model.Azienda;
import it.unicam.IDS2425.model.Utente;
import it.unicam.IDS2425.model.Ruolo;

public interface UtenteFactory {
    Utente creaUtente(String nome, String cognome, String email, String password, Ruolo ruolo, Azienda azienda);
}

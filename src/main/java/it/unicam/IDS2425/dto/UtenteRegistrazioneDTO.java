package it.unicam.IDS2425.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO per la registrazione di un nuovo utente.
 * Contiene le informazioni necessarie per creare un account utente.
 */
@Setter
@Getter
public class UtenteRegistrazioneDTO {
    /**
     * Il nome dell'utente.
     */
    private String nome;

    /**
     * Il cognome dell'utente.
     */
    private String cognome;

    /**
     * L'email dell'utente.
     */
    private String email;

    /**
     * La password dell'utente.
     */
    private String password;

    /**
     * Il ruolo assegnato all'utente.
     */
    private String ruolo;

    /**
     * L'ID dell'azienda a cui l'utente è associato.
     */
    private String aziendaId;
}
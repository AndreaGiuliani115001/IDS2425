package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe che rappresenta un invito a un evento nel sistema.
 * Un invito è associato a un evento, un utente e un animatore.
 */
@Setter
@Getter
@Document(collection = "inviti")
public class Invito {

    /**
     * Identificativo univoco dell'invito.
     */
    private String id;

    /**
     * Evento a cui è associato l'invito.
     */
    @DBRef
    private Evento evento;

    /**
     * Utente invitato all'evento.
     */
    @DBRef
    private Utente utente;

    /**
     * Animatore che ha inviato l'invito.
     */
    @DBRef
    private Animatore animatore;

    /**
     * Stato di conferma dell'invito.
     */
    private boolean conferma;

    /**
     * Costruttore per creare un invito.
     *
     * @param id Identificativo dell'invito.
     * @param evento Evento associato all'invito.
     * @param utente Utente invitato.
     * @param animatore Animatore che ha inviato l'invito.
     */
    public Invito(String id, Evento evento, Utente utente, Animatore animatore) {
        this.id = id;
        this.evento = evento;
        this.utente = utente;
        this.animatore = animatore;
        this.conferma = false;
    }
}
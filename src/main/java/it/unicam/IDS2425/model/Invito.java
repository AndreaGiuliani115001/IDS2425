package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inviti")
public class Invito {

    @Setter
    @Getter
    private String id;

    @DBRef
    private Evento evento;

    @DBRef
    private Utente utente;

    @DBRef
    private Animatore animatore;

    private boolean conferma;

    public Invito(String id, Evento evento, Utente utente, Animatore animatore) {
        this.id = id;
        this.evento = evento;
        this.utente = utente;
        this.animatore = animatore;
        this.conferma = false;
    }

}

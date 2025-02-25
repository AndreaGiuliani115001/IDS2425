package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe che rappresenta un evento nel sistema.
 * Un evento è organizzato da un'azienda e può avere partecipanti.
 */
@Setter
@Getter
@Document(collection = "eventi")
public class Evento {

    /**
     * Identificativo univoco dell'evento.
     */
    @Id
    private String id;

    /**
     * Nome dell'evento.
     */
    private String nome;

    /**
     * Descrizione dell'evento.
     */
    private String descrizione;

    /**
     * Luogo in cui si svolge l'evento.
     */
    private String luogo;

    /**
     * Data di inizio dell'evento.
     */
    private Date dataInizio;

    /**
     * Data di fine dell'evento.
     */
    private Date dataFine;

    /**
     * Lista degli utenti partecipanti all'evento.
     */
    @DBRef
    private List<Utente> partecipanti;

    /**
     * Azienda organizzatrice dell'evento.
     */
    @DBRef
    private Azienda aziendaOrganizzante;

    /**
     * Costruttore per creare un evento.
     *
     * @param nome Nome dell'evento.
     * @param descrizione Descrizione dell'evento.
     * @param dataInizio Data di inizio dell'evento.
     * @param dataFine Data di fine dell'evento.
     * @param luogo Luogo in cui si svolge l'evento.
     * @param aziendaOrganizzante Azienda che organizza l'evento.
     */
    public Evento(String nome, String descrizione, Date dataInizio, Date dataFine, String luogo, Azienda aziendaOrganizzante) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.aziendaOrganizzante = aziendaOrganizzante;
        this.partecipanti = new ArrayList<>();
    }
}
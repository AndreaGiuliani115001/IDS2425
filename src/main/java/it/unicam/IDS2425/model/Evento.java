package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Document(collection = "eventi")
public class Evento {

    // Getters e setters
    @Id
    private String id;
    private String nome;
    private String descrizione;
    private String luogo;
    private Date dataInizio;
    private Date dataFine;

    @DBRef
    private List<Utente> partecipanti;

    @DBRef
    private Azienda aziendaOrganizzante;

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

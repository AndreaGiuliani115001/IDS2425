package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Classe che rappresenta un contenuto associato a un prodotto.
 * Un contenuto può includere file, una descrizione e uno stato di validazione.
 */
@Getter
@Setter
@Document(collection = "contenuti")
public class Contenuto {

    /**
     * Identificativo univoco del contenuto.
     */
    @Id
    private String id;

    /**
     * Nome del contenuto.
     */
    private String nome;

    /**
     * Descrizione del contenuto.
     */
    private String descrizione;

    /**
     * Lista di URL dei file associati al contenuto.
     */
    private List<String> fileUrl;

    /**
     * Stato di validazione del contenuto.
     */
    private boolean statoValidazione;

    /**
     * Data di creazione del contenuto.
     */
    private Date data;

    /**
     * Il prodotto a cui è associato il contenuto.
     */
    @DBRef
    private Prodotto prodotto;

    /**
     * Costruttore per creare un nuovo contenuto.
     *
     * @param nome Nome del contenuto.
     * @param descrizione Descrizione del contenuto.
     * @param fileUrl Lista di URL dei file associati.
     * @param data Data di creazione del contenuto.
     * @param prodotto Prodotto a cui è associato il contenuto.
     */
    public Contenuto(String nome, String descrizione, List<String> fileUrl, Date data, Prodotto prodotto) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.fileUrl = fileUrl;
        this.data = data;
        this.prodotto = prodotto;
        this.statoValidazione = false;
    }
}
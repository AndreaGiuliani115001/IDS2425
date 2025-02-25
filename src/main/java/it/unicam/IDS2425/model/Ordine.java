package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Classe che rappresenta un ordine nel sistema.
 * Un ordine è associato a un carrello e contiene una lista di prodotti acquistati.
 */
@Getter
@Setter
@Document(collection = "carrelli")
public class Ordine {

    /**
     * Identificativo univoco dell'ordine.
     */
    @Id
    private String id;

    /**
     * Data di creazione dell'ordine.
     */
    private Date data;

    /**
     * Totale del prezzo dell'ordine.
     */
    private double totalePrezzo;

    /**
     * Lista dei prodotti acquistati nell'ordine.
     */
    @DBRef
    private List<Prodotto> prodotti;

    /**
     * Carrello associato all'ordine.
     */
    @DBRef
    private Carrello carrello;

    /**
     * Costruttore per creare un ordine.
     *
     * @param data Data di creazione dell'ordine.
     * @param carrello Carrello da cui è stato generato l'ordine.
     * @param prodotti Lista di prodotti acquistati.
     * @param totalePrezzo Totale del prezzo dell'ordine.
     */
    public Ordine(Date data, Carrello carrello, List<Prodotto> prodotti, double totalePrezzo) {
        this.data = data;
        this.prodotti = prodotti;
        this.totalePrezzo = totalePrezzo;
        this.carrello = carrello;
    }
}

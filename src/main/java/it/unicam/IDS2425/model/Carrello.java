package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un carrello della spesa nel sistema.
 * Contiene i prodotti selezionati dall'acquirente e il totale del prezzo.
 */
@Getter
@Setter
@Document(collection = "carrelli")
public class Carrello {

    /**
     * Identificativo univoco del carrello.
     */
    @Id
    private String id;

    /**
     * L'acquirente proprietario del carrello.
     */
    @DBRef
    private Acquirente proprietario;

    /**
     * Lista dei prodotti contenuti nel carrello.
     */
    @DBRef
    private List<Prodotto> prodotti;

    /**
     * Totale del prezzo dei prodotti nel carrello.
     */
    private double totalePrezzo;

    /**
     * Costruttore per creare un carrello associato a un acquirente.
     *
     * @param proprietario L'acquirente proprietario del carrello.
     */
    public Carrello(Acquirente proprietario) {
        this.proprietario = proprietario;
        this.prodotti = new ArrayList<>();
        this.totalePrezzo = 0;
    }
}
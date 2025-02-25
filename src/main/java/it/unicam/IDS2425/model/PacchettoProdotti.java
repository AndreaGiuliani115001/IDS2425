package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Classe che rappresenta un pacchetto di prodotti nel sistema.
 * Un pacchetto di prodotti è un insieme di prodotti venduti insieme a un prezzo specifico.
 */
@Getter
@Setter
@Document(collection = "PacchettiProdotti")
public class PacchettoProdotti {

    /**
     * Identificativo univoco del pacchetto di prodotti.
     */
    @Id
    private String id;

    /**
     * Nome del pacchetto di prodotti.
     */
    private String nome;

    /**
     * Descrizione del pacchetto di prodotti.
     */
    private String descrizione;

    /**
     * Prezzo del pacchetto di prodotti.
     */
    private double prezzo;

    /**
     * Lista dei prodotti inclusi nel pacchetto.
     */
    @DBRef
    private List<Prodotto> prodotti;

    /**
     * Azienda proprietaria del pacchetto di prodotti.
     */
    @DBRef
    private Azienda aziendaProprietaria;

    /**
     * Costruttore per creare un pacchetto di prodotti.
     *
     * @param nome Nome del pacchetto.
     * @param descrizione Descrizione del pacchetto.
     * @param prezzo Prezzo del pacchetto.
     * @param prodotti Lista di prodotti inclusi nel pacchetto.
     * @param aziendaProprietaria Azienda proprietaria del pacchetto.
     */
    public PacchettoProdotti(String nome, String descrizione, double prezzo, List<Prodotto> prodotti, Azienda aziendaProprietaria) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.prodotti = prodotti;
        this.aziendaProprietaria = aziendaProprietaria;
    }
}
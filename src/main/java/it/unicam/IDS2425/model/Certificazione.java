package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Classe che rappresenta una certificazione associata a un prodotto.
 * Estende la classe Contenuto e include informazioni sulla data di rilascio e scadenza.
 */
@Getter
@Setter
@Document(collection = "contenuti")
public class Certificazione extends Contenuto {

    /**
     * Data di rilascio della certificazione.
     */
    private Date dataRilascio;

    /**
     * Data di scadenza della certificazione.
     */
    private Date dataScadenza;

    /**
     * Costruttore per creare un'istanza di certificazione.
     *
     * @param nome Nome della certificazione.
     * @param descrizione Descrizione della certificazione.
     * @param fileUrl Lista di URL dei file associati alla certificazione.
     * @param data Data di creazione della certificazione.
     * @param prodotto Il prodotto a cui è associata la certificazione.
     * @param dataRilascio Data di rilascio della certificazione.
     * @param dataScadenza Data di scadenza della certificazione.
     */
    public Certificazione(String nome, String descrizione, List<String> fileUrl, Date data, Prodotto prodotto, Date dataRilascio, Date dataScadenza) {
        super(nome, descrizione, fileUrl, data, prodotto);
        this.dataRilascio = dataRilascio;
        this.dataScadenza = dataScadenza;
    }
}
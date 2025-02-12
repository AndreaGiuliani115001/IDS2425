package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "contenuti")
public class Certificazione extends Contenuto {

    private Date dataRilascio;
    private Date dataScadenza;

    public Certificazione(String nome, String descrizione, List<String> fileUrl, Date data, Prodotto prodotto, Date dataRilascio, Date dataScadenza) {
        super(nome, descrizione, fileUrl, data, prodotto);
        this.dataRilascio = dataRilascio;
        this.dataScadenza = dataScadenza;
    }
}

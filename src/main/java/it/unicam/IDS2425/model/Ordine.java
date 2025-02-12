package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter

@Document(collection = "carrelli")
public class Ordine {

    @Id
    private String id;

    private String codice;
    private Date data;
    private double totalePrezzo;

    @DBRef
    private List<Prodotto> prodotti;

    @DBRef
    private Acquirente acquirente;

    public Ordine(String codice, Date data, Acquirente acquirent, List<Prodotto> prodotti, double totalePrezzo) {
        this.acquirente = acquirente;
        this.data = data;
        this.codice = codice;
        this.prodotti = prodotti;
        this.totalePrezzo = totalePrezzo;
    }
}

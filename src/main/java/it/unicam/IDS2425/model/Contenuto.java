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
@Document(collection = "contenuti")
public class Contenuto {

    @Id
    private String id;
    private String nome;
    private String descrizione;
    private List<String> fileUrl;
    private boolean statoValidazione;
    private Date data;

    @DBRef
    private Prodotto prodotto;

    public Contenuto(String nome, String descrizione, List<String> fileUrl, Date data, Prodotto prodotto) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.fileUrl = fileUrl;
        this.data = data;
        this.prodotto = prodotto;
        this.statoValidazione = false;
    }

}


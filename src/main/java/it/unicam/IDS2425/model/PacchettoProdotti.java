package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "PacchettiProdotti")
public class PacchettoProdotti {

    @Id
    private String id;
    private String nome;
    private String descrizione;
    private double prezzo;

    @DBRef
    private List<Prodotto> prodotti;

    @DBRef
    private Azienda aziendaProprietaria;

    public PacchettoProdotti(String nome, String descrizione, double prezzo, List<Prodotto> prodotti, Azienda aziendaProprietaria) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.prodotti = prodotti;
        this.aziendaProprietaria = aziendaProprietaria;
    }

}

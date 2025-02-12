package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "prodotti")
public class Prodotto {



    @Id
    private String id;
    private String nome;
    private String descrizione;
    private boolean inVendita;
    private String categoria;
    private int quantita;
    private boolean trasformazione;

    @DBRef
    private Azienda aziendaProprietaria;

    @DBRef
    private List<PacchettoProdotti> pacchettiProdotti;

    @DBRef
    private List<Contenuto> contenuti;  // Relazione con i contenuti

    public Prodotto(String nome, String descrizione, String categoria, int quantita, Azienda aziendaProprietaria) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.inVendita = false;
        this.categoria = categoria;
        this.quantita = quantita;
        this.trasformazione = false;
        this.aziendaProprietaria = aziendaProprietaria;
        this.pacchettiProdotti = new ArrayList<>();
        this.contenuti = new ArrayList<Contenuto>();
    }

}

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
@Document(collection = "carrelli")
public class Carrello {

    @Id
    private String id;

    @DBRef
    private Utente proprietario;

    @DBRef
    private List<Prodotto> prodotti;

    private double totalePrezzo;

    public Carrello(Utente proprietario) {
        this.proprietario = proprietario;
        this.prodotti = new ArrayList<Prodotto>();
        this.totalePrezzo = 0;
    }

}


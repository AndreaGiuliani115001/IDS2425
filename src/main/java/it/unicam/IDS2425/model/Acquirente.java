package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Document(collection = "utenti")
public class Acquirente extends Utente{

    @DBRef
    private Carrello carrello;

    @DBRef
    private List<Ordine> ordini;

    public Acquirente(String nome, String cognome, String email, String password, Carrello carrello) {
        super(nome, cognome, email, password, Ruolo.ACQUIRENTE);
        this.carrello = carrello;
        this.ordini = new ArrayList<>();
    }

    @Override
    public void eseguiAzioneSpecificata() {

    }

    @Override
    public void condividiInformazioni(Prodotto prodotto, Contenuto contenuto) {

    }
}

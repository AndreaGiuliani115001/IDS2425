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
public class Trasformatore extends Utente {

    @DBRef
    private List<Prodotto> prodottiTrasformati;

    @DBRef
    private List<Contenuto> contenutiCaricati;

    public Trasformatore(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password, Ruolo.TRASFORMATORE);
        this.prodottiTrasformati = new ArrayList<Prodotto>();
        this.contenutiCaricati = new ArrayList<Contenuto>();
    }

    @Override
    public void eseguiAzioneSpecificata() {

    }

    @Override
    public void condividiInformazioni(Prodotto prodotto, Contenuto contenuto) {

    }

    public void trasformaProdotto(Prodotto prodotto) {
        System.out.println("Il trasformatore sta trasformando il prodotto: " + prodotto.getNome());
    }

    public void caricaContenuto(Contenuto contenuto) {
        System.out.println("Contenuto '" + contenuto.getDescrizione() + "' caricato con successo dal Produttore.");
    }
}

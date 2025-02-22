package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "utenti")
public class Produttore extends Utente {

    @DBRef
    private List<Prodotto> prodottiCaricati;

    @DBRef
    private List<Contenuto> contenutiCaricati;

    public Produttore(String nome, String cognome, String email, String password, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.PRODUTTORE, azienda);
        this.prodottiCaricati = new ArrayList<Prodotto>();
        this.contenutiCaricati = new ArrayList<Contenuto>();
    }

    @Override
    public void eseguiAzioneSpecificata() {
        System.out.println("Il Produttore può caricare prodotti nel sistema.");
    }

    @Override
    public void condividiInformazioni(Prodotto prodotto, Contenuto contenuto) {
        System.out.println("Il Produttore sta condividendo informazioni");
    }

    /**
     * Metodo specifico per caricare un nuovo prodotto.
     */
    public void caricaProdotto(Prodotto prodotto) {
        System.out.println("Prodotto '" + prodotto.getNome() + "' caricato con successo dal Produttore.");
    }

    /**
     * Metodo specifico per caricare un nuovo contenuto.
     */
    public void caricaContenuto(Contenuto contenuto) {
        System.out.println("Contenuto '" + contenuto.getDescrizione() + "' caricato con successo dal Produttore.");
    }

    @Override
    public String toString() {
        return "Produttore{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}

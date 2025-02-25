package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un produttore nel sistema.
 * Un produttore è un utente che può caricare prodotti e contenuti associati.
 */
@Getter
@Setter
@Document(collection = "utenti")
public class Produttore extends Utente {

    /**
     * Lista dei prodotti caricati dal produttore.
     */
    @DBRef
    private List<Prodotto> prodottiCaricati;

    /**
     * Lista dei contenuti caricati dal produttore.
     */
    @DBRef
    private List<Contenuto> contenutiCaricati;

    /**
     * Costruttore per creare un produttore.
     *
     * @param nome Il nome del produttore.
     * @param cognome Il cognome del produttore.
     * @param email L'email del produttore.
     * @param password La password del produttore.
     * @param azienda L'azienda a cui il produttore è associato.
     */
    public Produttore(String nome, String cognome, String email, String password, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.PRODUTTORE, azienda);
        this.prodottiCaricati = new ArrayList<>();
        this.contenutiCaricati = new ArrayList<>();
    }

    /**
     * Restituisce una rappresentazione testuale del produttore.
     *
     * @return Stringa contenente le informazioni del produttore.
     */
    @Override
    public String toString() {
        return "Produttore{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
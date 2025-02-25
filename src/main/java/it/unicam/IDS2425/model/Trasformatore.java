package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un trasformatore nel sistema.
 * Un trasformatore è un utente che può trasformare prodotti e caricare contenuti.
 */
@Setter
@Getter
@Document(collection = "utenti")
public class Trasformatore extends Utente {

    /**
     * Lista dei prodotti trasformati dal trasformatore.
     */
    @DBRef
    private List<Prodotto> prodottiTrasformati;

    /**
     * Lista dei contenuti caricati dal trasformatore.
     */
    @DBRef
    private List<Contenuto> contenutiCaricati;

    /**
     * Costruttore per creare un trasformatore.
     *
     * @param nome Il nome del trasformatore.
     * @param cognome Il cognome del trasformatore.
     * @param email L'email del trasformatore.
     * @param password La password del trasformatore.
     * @param azienda L'azienda a cui il trasformatore è associato.
     */
    public Trasformatore(String nome, String cognome, String email, String password, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.TRASFORMATORE, azienda);
        this.prodottiTrasformati = new ArrayList<>();
        this.contenutiCaricati = new ArrayList<>();
    }

    /**
     * Restituisce una rappresentazione testuale del trasformatore.
     *
     * @return Stringa contenente le informazioni del trasformatore.
     */
    @Override
    public String toString() {
        return "Trasformatore{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
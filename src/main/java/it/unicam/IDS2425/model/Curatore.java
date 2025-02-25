package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un curatore nel sistema.
 * Il curatore è un utente responsabile della validazione di contenuti e prodotti.
 */
@Getter
@Setter
@Document(collection = "utenti")
public class Curatore extends Utente {

    /**
     * Lista dei contenuti validati dal curatore.
     */
    @DBRef
    private List<Contenuto> ContenutiValidati;

    /**
     * Lista dei prodotti validati dal curatore.
     */
    @DBRef
    private List<Prodotto> ProdottiValidati;

    /**
     * Costruttore per creare un curatore.
     *
     * @param nome Il nome del curatore.
     * @param cognome Il cognome del curatore.
     * @param email L'email del curatore.
     * @param password La password del curatore.
     * @param azienda L'azienda a cui il curatore è associato.
     */
    public Curatore(String nome, String cognome, String email, String password, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.CURATORE, azienda);
        this.ContenutiValidati = new ArrayList<>();
        this.ProdottiValidati = new ArrayList<>();
    }

    /**
     * Restituisce una rappresentazione testuale del curatore.
     *
     * @return Stringa contenente le informazioni del curatore.
     */
    @Override
    public String toString() {
        return "Curatore{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
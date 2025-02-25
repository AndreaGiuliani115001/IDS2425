package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un distributore nel sistema.
 * Un distributore è un utente che può mettere in vendita prodotti e creare pacchetti di prodotti.
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "utenti")
public class Distributore extends Utente {

    /**
     * Lista dei prodotti messi in vendita dal distributore.
     */
    @DBRef
    private List<Prodotto> prodottiMessiInVendita;

    /**
     * Lista dei pacchetti di prodotti creati dal distributore.
     */
    @DBRef
    private List<PacchettoProdotti> pacchettiProdottiCreati;

    /**
     * Costruttore per creare un distributore.
     *
     * @param nome Il nome del distributore.
     * @param cognome Il cognome del distributore.
     * @param email L'email del distributore.
     * @param password La password del distributore.
     * @param azienda L'azienda a cui il distributore è associato.
     */
    public Distributore(String nome, String cognome, String email, String password, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.DISTRIBUTORE, azienda);
        this.prodottiMessiInVendita = new ArrayList<>();
        this.pacchettiProdottiCreati = new ArrayList<>();
    }

    /**
     * Restituisce una rappresentazione testuale del distributore.
     *
     * @return Stringa contenente le informazioni del distributore.
     */
    @Override
    public String toString() {
        return "Distributore{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
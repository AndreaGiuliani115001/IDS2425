package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe che rappresenta un acquirente nel sistema.
 * L'acquirente è un tipo di utente che possiede un carrello per effettuare acquisti.
 */
@Setter
@Getter
@Document(collection = "utenti")
public class Acquirente extends Utente {

    /**
     * Il carrello associato all'acquirente.
     */
    @DBRef
    private Carrello carrello;

    /**
     * Costruttore per creare un acquirente.
     *
     * @param nome Il nome dell'acquirente.
     * @param cognome Il cognome dell'acquirente.
     * @param email L'email dell'acquirente.
     * @param password La password dell'acquirente.
     * @param carrello Il carrello associato all'acquirente.
     * @param azienda L'azienda a cui l'acquirente è associato.
     */
    public Acquirente(String nome, String cognome, String email, String password, Carrello carrello, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.ACQUIRENTE, azienda);
        this.carrello = carrello;
    }

    /**
     * Restituisce una rappresentazione testuale dell'acquirente.
     *
     * @return Stringa contenente le informazioni dell'acquirente.
     */
    @Override
    public String toString() {
        return "Acquirente{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
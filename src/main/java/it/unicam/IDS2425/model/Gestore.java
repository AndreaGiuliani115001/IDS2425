package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe che rappresenta un gestore nel sistema.
 * Il gestore è un utente con privilegi amministrativi per la gestione dell'azienda.
 */
@Setter
@Getter
@Document(collection = "utenti")
public class Gestore extends Utente {

    /**
     * Costruttore per creare un gestore.
     *
     * @param nome Il nome del gestore.
     * @param cognome Il cognome del gestore.
     * @param email L'email del gestore.
     * @param password La password del gestore.
     * @param azienda L'azienda a cui il gestore è associato.
     */
    public Gestore(String nome, String cognome, String email, String password, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.GESTORE, azienda);
    }

    /**
     * Restituisce una rappresentazione testuale del gestore.
     *
     * @return Stringa contenente le informazioni del gestore.
     */
    @Override
    public String toString() {
        return "Gestore{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}

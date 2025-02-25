package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un animatore nel sistema.
 * L'animatore è un utente che può organizzare eventi.
 */
@Setter
@Getter
@Document(collection = "utenti")
public class Animatore extends Utente {

    /**
     * Lista degli eventi organizzati dall'animatore.
     */
    @DBRef
    private List<Evento> eventiOrganizzati;

    /**
     * Costruttore per creare un animatore.
     *
     * @param nome Il nome dell'animatore.
     * @param cognome Il cognome dell'animatore.
     * @param email L'email dell'animatore.
     * @param password La password dell'animatore.
     * @param azienda L'azienda a cui l'animatore è associato.
     */
    public Animatore(String nome, String cognome, String email, String password, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.ANIMATORE, azienda);
        this.eventiOrganizzati = new ArrayList<>();
    }

    /**
     * Restituisce una rappresentazione testuale dell'animatore.
     *
     * @return Stringa contenente le informazioni dell'animatore.
     */
    @Override
    public String toString() {
        return "Animatore{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
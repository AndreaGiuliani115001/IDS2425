package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "utenti")
public class Animatore extends Utente {

    @DBRef
    private List<Evento> eventiOrganizzati;

    public Animatore(String nome, String cognome, String email, String password, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.ANIMATORE, azienda);
        this.eventiOrganizzati = new ArrayList<Evento>();
    }

    public List<Evento> getEventiOrganizzati() {
        return eventiOrganizzati;
    }

    public void setEventiOrganizzati(List<Evento> eventiOrganizzati) {
        this.eventiOrganizzati = eventiOrganizzati;
    }

    @Override
    public void eseguiAzioneSpecificata() {
        System.out.println("L'Animatore della Filiera può organizzare eventi e fiere.");
    }

    @Override
    public void condividiInformazioni(Prodotto prodotto, Contenuto contenuto) {
        System.out.println("L'Animatore sta condividendo informazioni.");
    }

    /**
     * Metodo per organizzare un evento.
     * @param nome dell'evento.
     * @param descrizione dell'evento.
     * @param luogo dell'evento.
     * @param dataInizio dell'evento.
     * @param datafine dell'evento.
     * @param partecipanti dell'evento.
     */
    public void organizzaEvento(String nome, String descrizione, String luogo, Date dataInizio, Date datafine, List<Utente> partecipanti) {
        System.out.println("Evento '" + nome + "' organizzato dall'Animatore.");

    }

    @Override
    public String toString() {
        return "Animatore{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}

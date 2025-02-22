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
public class Curatore extends Utente {

    @DBRef
    private List<Contenuto> ContenutiValidati;

    public Curatore(String nome, String cognome, String email, String password, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.CURATORE, azienda);
        this.ContenutiValidati = new ArrayList<Contenuto>();
    }

    @Override
    public void eseguiAzioneSpecificata() {
        System.out.println("Il Curatore può validare i contenuti caricati dai produttori.");
    }

    @Override
    public void condividiInformazioni(Prodotto prodotto, Contenuto contenuto) {
        System.out.println("Il Curatore sta condividendo contenuti.");
    }

    public List<Contenuto> getContenutiValidati() {
        return ContenutiValidati;
    }

    public void setContenutiValidati(List<Contenuto> contenutiValidati) {
        ContenutiValidati = contenutiValidati;
    }

    /**
     * Metodo per validare un contenuto.
     * @param contenuto Contenuto da validare.
     */
    public void validaContenuto(Contenuto contenuto) {
        if (contenuto != null) {
            contenuto.setStatoValidazione(true);
            System.out.println("Contenuto '" + contenuto.getDescrizione() + "' validato correttamente.");
        } else {
            System.out.println("Contenuto non valido o nullo.");
        }
    }

    /**
     * Metodo per rifiutare un contenuto con un feedback.
     * @param contenuto Contenuto da rifiutare.
     * @param feedback  Commento del curatore sul motivo del rifiuto.
     */
    public void rifiutaContenuto(Contenuto contenuto, String feedback) {
        if (contenuto != null) {
            contenuto.setStatoValidazione(false);
            System.out.println("Contenuto '" + contenuto.getDescrizione() + "' rifiutato. Feedback: " + feedback);
        } else {
            System.out.println("Contenuto non valido o nullo.");
        }
    }

    @Override
    public String toString() {
        return "Curatore{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}

package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Setter
@Getter
@Document(collection = "utenti")
public class Gestore extends Utente{

    public Gestore(String nome, String cognome, String email, String password, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.GESTORE, azienda);
    }


    @Override
    public void eseguiAzioneSpecificata() {
        System.out.println("Il Gestore può modificare i permessi");
    }

    @Override
    public void condividiInformazioni(Prodotto prodotto, Contenuto contenuto) {
        System.out.println("Il Gestore sta condividendo informazioni");
    }
}

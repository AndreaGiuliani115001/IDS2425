package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "utenti")
public class Distributore extends Utente{

    private List<Prodotto> prodottiInVendita;

    public Distributore(String nome, String cognome, String email, String password, Azienda azienda) {
        super(nome, cognome, email, password, Ruolo.DISTRIBUTORE, azienda);
        this.prodottiInVendita = new ArrayList<>();
    }

    @Override
    public void eseguiAzioneSpecificata() {

    }

    @Override
    public void condividiInformazioni(Prodotto prodotto, Contenuto contenuto) {

    }
}

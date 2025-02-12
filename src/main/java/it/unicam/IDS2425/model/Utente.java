package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "utenti")
public abstract class Utente {
    @Id
    private String id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Ruolo ruolo;

    public Utente(String nome, String cognome, String email, String password, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }

    public abstract void eseguiAzioneSpecificata();

    public abstract void condividiInformazioni(Prodotto prodotto, Contenuto contenuto);
}

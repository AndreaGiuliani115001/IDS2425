package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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

    @DBRef
    private Azienda azienda;

    public Utente(String nome, String cognome, String email, String password, Ruolo ruolo, Azienda azienda) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
        this.azienda = azienda;
    }

    public abstract void eseguiAzioneSpecificata();

    public abstract void condividiInformazioni(Prodotto prodotto, Contenuto contenuto);
}

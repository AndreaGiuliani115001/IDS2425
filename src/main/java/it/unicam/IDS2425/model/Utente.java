package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Classe astratta che rappresenta un utente del sistema.
 * Ogni utente ha un ruolo specifico e appartiene a un'azienda.
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "utenti")
public abstract class Utente {

    /**
     * Identificativo univoco dell'utente.
     */
    @Id
    private String id;

    /**
     * Nome dell'utente.
     */
    private String nome;

    /**
     * Cognome dell'utente.
     */
    private String cognome;

    /**
     * Email dell'utente.
     */
    private String email;

    /**
     * Password dell'utente.
     */
    private String password;

    /**
     * Ruolo dell'utente nel sistema.
     */
    private Ruolo ruolo;

    /**
     * Azienda a cui l'utente è associato.
     */
    @DBRef
    private Azienda azienda;

    /**
     * Data di creazione dell'utente.
     */
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * Data dell'ultima modifica delle informazioni dell'utente.
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * Costruttore parametrizzato per creare un nuovo utente.
     *
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param email L'email dell'utente.
     * @param password La password dell'utente.
     * @param ruolo Il ruolo dell'utente.
     * @param azienda L'azienda a cui l'utente è associato.
     */
    public Utente(String nome, String cognome, String email, String password, Ruolo ruolo, Azienda azienda) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
        this.azienda = azienda;
    }
}

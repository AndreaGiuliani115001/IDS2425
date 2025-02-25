package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Classe che rappresenta un'azienda nel sistema.
 * Contiene informazioni relative alla ragione sociale, partita IVA, contatti e date di creazione/modifica.
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "aziende")
public class Azienda {

    /**
     * Identificativo univoco dell'azienda.
     */
    @Id
    private String id;

    /**
     * Ragione sociale dell'azienda.
     */
    private String ragioneSociale;

    /**
     * Partita IVA dell'azienda.
     */
    private String pIva;

    /**
     * Indirizzo dell'azienda.
     */
    private String indirizzo;

    /**
     * Email di contatto dell'azienda.
     */
    private String email;

    /**
     * Numero di telefono dell'azienda.
     */
    private String numeroTelefono;

    /**
     * Data di creazione dell'azienda.
     */
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * Data dell'ultima modifica delle informazioni aziendali.
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * Costruttore per creare un'azienda con i parametri principali.
     *
     * @param ragioneSociale La ragione sociale dell'azienda.
     * @param pIva La partita IVA dell'azienda.
     * @param indirizzo L'indirizzo dell'azienda.
     * @param email L'email di contatto dell'azienda.
     * @param numeroTelefono Il numero di telefono dell'azienda.
     */
    public Azienda(String ragioneSociale, String pIva, String indirizzo, String email, String numeroTelefono) {
        this.ragioneSociale = ragioneSociale;
        this.pIva = pIva;
        this.indirizzo = indirizzo;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
    }

    /**
     * Restituisce una rappresentazione testuale dell'azienda.
     *
     * @return Stringa contenente le informazioni dell'azienda.
     */
    @Override
    public String toString() {
        return "Azienda{" +
                "ragioneSociale='" + getRagioneSociale() + '\'' +
                ", pIva='" + getPIva() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
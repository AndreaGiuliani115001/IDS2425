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
 * Classe che rappresenta un prodotto nel sistema.
 * Un prodotto può appartenere a una categoria specifica, essere trasformato e avere uno stato.
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "prodotti")
public class Prodotto {

    /**
     * Identificativo univoco del prodotto.
     */
    @Id
    private String id;

    /**
     * Nome del prodotto.
     */
    private String nome;

    /**
     * Descrizione del prodotto.
     */
    private String descrizione;

    /**
     * Categoria del prodotto.
     */
    private CategoriaProdotto categoria;

    /**
     * Quantità disponibile del prodotto.
     */
    private int quantita;

    /**
     * Il prezzo del prodotto.
     */
    private double prezzo;

    /**
     * Indica se il prodotto è stato trasformato.
     */
    private boolean trasformato;

    /**
     * Stato attuale del prodotto (IN_CREAZIONE, VALIDATO, IN_VENDITA).
     */
    private StatoProdotto statoProdotto;

    /**
     * Azienda proprietaria del prodotto.
     */
    @DBRef
    private Azienda aziendaProprietaria;

    /**
     * Se il prodotto è trasformato, mantiene il riferimento al prodotto originale.
     */
    @DBRef
    private Prodotto prodottoOriginale;

    /**
     * Data di creazione del prodotto.
     */
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * Data dell'ultima modifica del prodotto.
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * Costruttore per la creazione di un nuovo prodotto da parte di un produttore.
     *
     * @param nome Nome del prodotto.
     * @param descrizione Descrizione del prodotto.
     * @param categoria Categoria del prodotto.
     * @param quantita Quantità disponibile.
     * @param aziendaProprietaria Azienda proprietaria del prodotto.
     */
    public Prodotto(String nome, String descrizione, CategoriaProdotto categoria, int quantita, double prezzo, Azienda aziendaProprietaria) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.aziendaProprietaria = aziendaProprietaria;
        this.trasformato = false;
        this.statoProdotto = StatoProdotto.IN_CREAZIONE;
        this.prodottoOriginale = null;
    }
}

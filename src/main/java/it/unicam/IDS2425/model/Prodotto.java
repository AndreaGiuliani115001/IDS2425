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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "prodotti")
public class Prodotto {

    @Id
    private String id;
    private String nome;
    private String descrizione;
    private CategoriaProdotto categoria;
    private int quantita;
    private boolean trasformato; // True se il prodotto è trasformato
    private StatoProdotto statoProdotto; // Stato del prodotto (IN_CREAZIONE, VALIDATO, IN_VENDITA)

    @DBRef
    private Azienda aziendaProprietaria; // Azienda che possiede il prodotto

    @DBRef
    private Prodotto prodottoOriginale; // Se trasformato, mantiene il riferimento al prodotto originale

    @DBRef
    private List<Contenuto> contenuti; // Contenuti associati al prodotto

    @CreatedDate
    private LocalDateTime createdAt; // Data di creazione

    @LastModifiedDate
    private LocalDateTime updatedAt; // Ultima modifica

    /**
     * Costruttore per la creazione di un nuovo prodotto da parte di un produttore.
     */
    public Prodotto(String nome, String descrizione, CategoriaProdotto categoria, int quantita, Azienda aziendaProprietaria) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.quantita = quantita;
        this.aziendaProprietaria = aziendaProprietaria;
        this.trasformato = false;
        this.contenuti = new ArrayList<>();
        this.statoProdotto = StatoProdotto.IN_CREAZIONE;
        this.prodottoOriginale = null;
    }

    /**
     * Metodo per creare un prodotto trasformato a partire da un prodotto originale.
     * Solo il trasformatore può creare prodotti trasformati.
     */
    public static Prodotto trasformaProdotto(Prodotto originale, String nuovoNome, String nuovaDescrizione, CategoriaProdotto nuovaCategoria, int quantita, Azienda aziendaTrasformatrice) {
        if (originale.statoProdotto != StatoProdotto.VALIDATO) {
            throw new IllegalStateException("Il prodotto originale deve essere validato prima di essere trasformato.");
        }

        Prodotto prodottoTrasformato = new Prodotto(nuovoNome, nuovaDescrizione, nuovaCategoria, quantita, aziendaTrasformatrice);
        prodottoTrasformato.setTrasformato(true);
        prodottoTrasformato.setProdottoOriginale(originale);
        return prodottoTrasformato;
    }

    /**
     * Metodo per mettere il prodotto in vendita (solo il proprietario può farlo).
     */
    public void mettiInVendita(Azienda azienda) {
        if (!azienda.equals(this.aziendaProprietaria)) {
            throw new IllegalStateException("Non puoi mettere in vendita un prodotto che non appartiene alla tua azienda.");
        }
        if (this.statoProdotto == StatoProdotto.IN_CREAZIONE) {
            throw new IllegalStateException("Il prodotto deve essere validato prima di essere messo in vendita.");
        }
        this.statoProdotto = StatoProdotto.IN_VENDITA;
    }

    /**
     * Metodo per aggiungere contenuti al prodotto.
     * Solo il produttore, trasformatore o distributore (se il prodotto è in vendita) possono farlo.
     */
    public void aggiungiContenuto(Azienda azienda, Contenuto contenuto) {
        if (!puoModificare(azienda)) {
            throw new IllegalArgumentException("Questa azienda non può aggiungere contenuti a questo prodotto.");
        }
        this.contenuti.add(contenuto);
    }

    /**
     * Controlla se l'azienda ha il diritto di manipolare il prodotto.
     */
    private boolean puoModificare(Azienda azienda) {
        return azienda.equals(this.aziendaProprietaria) || // Il produttore o trasformatore originale
                (this.statoProdotto == StatoProdotto.IN_VENDITA && azienda.equals(this.aziendaProprietaria)); // Il distributore se il prodotto è in vendita
    }
}

package it.unicam.IDS2425.dto;

import it.unicam.IDS2425.model.CategoriaProdotto;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO per la richiesta di creazione o modifica di un prodotto.
 * Contiene le informazioni necessarie per definire un prodotto.
 */
@Getter
@Setter
public class ProdottoRequestDTO {
    /**
     * Il nome del prodotto.
     */
    private String nome;

    /**
     * La descrizione del prodotto.
     */
    private String descrizione;

    /**
     * La categoria a cui appartiene il prodotto.
     */
    private CategoriaProdotto categoria;

    /**
     * La quantità disponibile del prodotto.
     */
    private int quantita;

    /**
     * Il prezzo del prodotto.
     */
    private double prezzo;
}
package it.unicam.IDS2425.model;

/**
 * Enum che rappresenta i diversi stati in cui un prodotto può trovarsi nel sistema.
 */
public enum StatoProdotto {
    /**
     * Il prodotto è stato creato dal produttore o trasformatore e non è ancora stato validato.
     */
    IN_CREAZIONE,

    /**
     * Il curatore ha approvato il prodotto.
     */
    VALIDATO,

    /**
     * Il prodotto è disponibile sul marketplace per la vendita.
     */
    IN_VENDITA
}
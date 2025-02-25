package it.unicam.IDS2425.model;

import lombok.Getter;

/**
 * Enum che rappresenta i diversi ruoli degli utenti nella piattaforma.
 */
@Getter
public enum Ruolo {
    /**
     * Produttore di prodotti agricoli.
     */
    PRODUTTORE("Produttore di prodotti agricoli"),

    /**
     * Trasformatore dei prodotti.
     */
    TRASFORMATORE("Trasformatore dei prodotti"),

    /**
     * Distributore di tipicità locali.
     */
    DISTRIBUTORE("Distributore di tipicità locali"),

    /**
     * Verificatore dei contenuti.
     */
    CURATORE("Verificatore dei contenuti"),

    /**
     * Organizzatore di eventi e fiere.
     */
    ANIMATORE("Organizzatore di eventi e fiere"),

    /**
     * Acquirente dei prodotti.
     */
    ACQUIRENTE("Acquirente dei prodotti"),

    /**
     * Gestore della piattaforma.
     */
    GESTORE("Gestore della piattaforma");

    /**
     * Descrizione del ruolo.
     */
    private final String descrizione;

    /**
     * Costruttore per assegnare la descrizione al ruolo.
     *
     * @param descrizione Descrizione del ruolo.
     */
    Ruolo(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Metodo per ottenere un enum Ruolo a partire da una stringa.
     *
     * @param ruolo Il nome del ruolo in formato stringa.
     * @return Il ruolo corrispondente.
     * @throws IllegalArgumentException Se il ruolo non è valido.
     */
    public static Ruolo fromString(String ruolo) {
        for (Ruolo r : Ruolo.values()) {
            if (r.name().equalsIgnoreCase(ruolo)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Ruolo non valido: " + ruolo);
    }
}
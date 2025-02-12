package it.unicam.IDS2425.model;

import lombok.Getter;

/**
 * Enum che rappresenta i diversi ruoli degli utenti nella piattaforma.
 */
@Getter
public enum Ruolo {
    PRODUTTORE("Produttore di prodotti agricoli"),
    TRASFORMATORE("Trasformatore dei prodotti"),
    DISTRIBUTORE("Distributore di tipicità locali"),
    CURATORE("Verificatore dei contenuti"),
    ANIMATORE("Organizzatore di eventi e fiere"),
    ACQUIRENTE("Acquirente dei prodotti"),
    GESTORE("Gestore della piattaforma");

    private final String descrizione;

    Ruolo(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Metodo per ottenere un enum a partire da una stringa (utile per conversioni).
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




package it.unicam.IDS2425.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UtenteRegistrazioneDTO {
    // Getters e Setters

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String ruolo;
    private String aziendaId;

}

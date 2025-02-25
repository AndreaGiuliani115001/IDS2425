package it.unicam.IDS2425.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO per la richiesta di login.
 * Contiene le credenziali dell'utente necessarie per l'autenticazione.
 */
@Getter
@Setter
public class LoginRequestDTO {
    /**
     * L'email dell'utente.
     */
    private String email;

    /**
     * La password dell'utente.
     */
    private String password;
}
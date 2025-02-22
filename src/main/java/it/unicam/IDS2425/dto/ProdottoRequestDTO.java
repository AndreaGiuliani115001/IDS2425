package it.unicam.IDS2425.dto;

import it.unicam.IDS2425.model.CategoriaProdotto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdottoRequestDTO {
    private String nome;
    private String descrizione;
    private CategoriaProdotto categoria;
    private int quantita;
}

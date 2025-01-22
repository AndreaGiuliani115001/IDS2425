package it.unicam.IDS2425.service.factory;

import it.unicam.IDS2425.model.Attore;
import it.unicam.IDS2425.model.Curatore;

public class CuratoreFactory extends AttoreFactory {
    @Override
    public Attore creaAttore(String nome) {
        return new Curatore(nome);
    }
}

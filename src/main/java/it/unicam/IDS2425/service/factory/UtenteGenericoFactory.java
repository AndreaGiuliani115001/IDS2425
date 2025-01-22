package it.unicam.IDS2425.service.factory;

import it.unicam.IDS2425.model.Attore;
import it.unicam.IDS2425.model.UtenteGenerico;

public class UtenteGenericoFactory extends AttoreFactory {
    @Override
    public Attore creaAttore(String nome) {
        return new UtenteGenerico(nome);
    }
}

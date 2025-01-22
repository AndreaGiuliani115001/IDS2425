package it.unicam.IDS2425.service.factory;

import it.unicam.IDS2425.model.Attore;
import it.unicam.IDS2425.model.Produttore;

public class ProduttoreFactory extends AttoreFactory {
    @Override
    public Attore creaAttore(String nome) {
        return new Produttore(nome);
    }
}

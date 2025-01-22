package it.unicam.IDS2425.service.factory;

import it.unicam.IDS2425.model.Acquirente;
import it.unicam.IDS2425.model.Attore;

public class AcquirenteFactory extends AttoreFactory {
    @Override
    public Attore creaAttore(String nome) {
        return new Acquirente(nome);
    }
}

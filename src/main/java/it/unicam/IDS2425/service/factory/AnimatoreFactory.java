package it.unicam.IDS2425.service.factory;

import it.unicam.IDS2425.model.Animatore;
import it.unicam.IDS2425.model.Attore;

public class AnimatoreFactory extends AttoreFactory {
    @Override
    public Attore creaAttore(String nome) {
        return new Animatore(nome);
    }
}

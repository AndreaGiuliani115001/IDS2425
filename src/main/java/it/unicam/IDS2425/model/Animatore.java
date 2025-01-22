package it.unicam.IDS2425.model;

public class Animatore extends Attore {
    public Animatore(String nome) {
        super(nome);
    }

    @Override
    public void eseguiAzione() {
        System.out.println(getNome() + " sta organizzando eventi.");
    }
}

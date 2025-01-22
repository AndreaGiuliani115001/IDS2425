package it.unicam.IDS2425.model;

public class Curatore extends Attore {
    public Curatore(String nome) {
        super(nome);
    }

    @Override
    public void eseguiAzione() {
        System.out.println(getNome() + " sta verificando i contenuti.");
    }
}

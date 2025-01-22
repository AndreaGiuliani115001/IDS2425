package it.unicam.IDS2425.model;

public class Acquirente extends Attore {
    public Acquirente(String nome) {
        super(nome);
    }

    @Override
    public void eseguiAzione() {
        System.out.println(getNome() + " sta acquistando prodotti.");
    }
}

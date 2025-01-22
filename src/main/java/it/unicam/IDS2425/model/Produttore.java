package it.unicam.IDS2425.model;

public class Produttore extends Attore {
    public Produttore(String nome) {
        super(nome);
    }

    @Override
    public void eseguiAzione() {
        System.out.println(getNome() + " sta caricando prodotti.");
    }
}
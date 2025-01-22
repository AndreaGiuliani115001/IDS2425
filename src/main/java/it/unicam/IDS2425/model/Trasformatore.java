package it.unicam.IDS2425.model;

public class Trasformatore extends Attore {

    public Trasformatore(String nome) {
        super(nome);
    }

    @Override
    public void eseguiAzione() {
        System.out.println(getNome() + " sta trasformando i prodotti.");
    }
}

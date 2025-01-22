package it.unicam.IDS2425.model;

public class Distributore extends Attore {
    public Distributore(String nome) {
        super(nome);
    }

    @Override
    public void eseguiAzione() {
        System.out.println(getNome() + " sta distribuendo i prodotti.");
    }
}

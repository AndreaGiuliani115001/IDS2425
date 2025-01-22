package it.unicam.IDS2425.model;

public abstract class Attore {
    private String nome;

    public Attore(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract void eseguiAzione();
}

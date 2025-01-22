package it.unicam.IDS2425.model;

public class UtenteGenerico extends Attore {
    public UtenteGenerico(String nome) {
        super(nome);
    }

    @Override
    public void eseguiAzione() {
        System.out.println(getNome() + " sta visualizzando informazioni.");
    }
}

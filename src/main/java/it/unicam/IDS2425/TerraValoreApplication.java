package it.unicam.IDS2425;

import it.unicam.IDS2425.model.Attore;
import it.unicam.IDS2425.service.factory.*;

public class TerraValoreApplication {
    public static void main(String[] args) {
        AttoreFactory factory;

        // Creazione di un Produttore
        factory = new ProduttoreFactory();
        Attore produttore = factory.creaAttore("Mario");
        produttore.eseguiAzione();

        // Creazione di un Trasformatore
        factory = new TrasformatoreFactory();
        Attore trasformatore = factory.creaAttore("Luigi");
        trasformatore.eseguiAzione();

        // Creazione di un Distributore
        factory = new DistributoreFactory();
        Attore distributore = factory.creaAttore("Anna");
        distributore.eseguiAzione();

        // Creazione di un Curatore
        factory = new CuratoreFactory();
        Attore curatore = factory.creaAttore("Giulia");
        curatore.eseguiAzione();

        // Creazione di un Animatore della Filiera
        factory = new AnimatoreFactory();
        Attore animatore = factory.creaAttore("Marco");
        animatore.eseguiAzione();

        // Creazione di un Acquirente
        factory = new AcquirenteFactory();
        Attore acquirente = factory.creaAttore("Sara");
        acquirente.eseguiAzione();

        // Creazione di un Utente Generico
        factory = new UtenteGenericoFactory();
        Attore utenteGenerico = factory.creaAttore("Francesco");
        utenteGenerico.eseguiAzione();
    }
}
package it.unicam.IDS2425.seeder;

import it.unicam.IDS2425.model.*;
import it.unicam.IDS2425.repository.AziendaRepository;
import it.unicam.IDS2425.repository.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UtenteRepository utenteRepository;
    private final AziendaRepository aziendaRepository;

    public DatabaseSeeder(UtenteRepository utenteRepository, AziendaRepository aziendaRepository) {
        this.utenteRepository = utenteRepository;
        this.aziendaRepository = aziendaRepository;
    }

    @Override
    public void run(String... args) {
        // Pulizia dei dati esistenti (opzionale)
        utenteRepository.deleteAll();
        aziendaRepository.deleteAll();

        // Creazione degli utenti iniziali
        Utente produttore = new Produttore(
                "Mario", "Rossi", "mario.rossi@example.com", "password123"
        );

        Utente curatore = new Curatore(
                "Luca", "Bianchi", "luca.bianchi@example.com", "password456"
        );

        // Salva gli utenti nel database
        utenteRepository.saveAll(Arrays.asList(produttore, curatore));

        // Creazione di un'azienda iniziale
        Azienda azienda = new Azienda(
                "Azienda Agricola Rossi",
                "1234",
                "Via delle Campagne, 10",
                "contatto@rossi.it",
                "+39 123 456 7890"
        );

        aziendaRepository.save(azienda);

        System.out.println("Database inizializzato con dati di esempio.");
    }
}

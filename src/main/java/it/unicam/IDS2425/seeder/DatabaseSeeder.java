package it.unicam.IDS2425.seeder;

import it.unicam.IDS2425.model.*;
import it.unicam.IDS2425.repository.AziendaRepository;
import it.unicam.IDS2425.repository.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UtenteRepository utenteRepository;
    private final AziendaRepository aziendaRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseSeeder(UtenteRepository utenteRepository, AziendaRepository aziendaRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.aziendaRepository = aziendaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        System.out.println("🔄 Inizializzazione del database...");

        // Pulizia dei dati esistenti SOLO SE IL DATABASE È VUOTO
        if (utenteRepository.count() == 0 && aziendaRepository.count() == 0) {
            utenteRepository.deleteAll();
            aziendaRepository.deleteAll();

            System.out.println("🗑 Database svuotato.");

            // Creazione di aziende
            Azienda azienda1 = new Azienda("Azienda Agricola Rossi", "1234", "Via delle Campagne, 10", "contatto@rossi.it", "+39 123 456 7890");
            Azienda azienda2 = new Azienda("Fattoria Bianchi", "5678", "Strada Verde, 20", "info@bianchi.it", "+39 987 654 3210");

            aziendaRepository.saveAll(List.of(azienda1, azienda2));
            System.out.println("🏢 Aziende create.");

            // Creazione degli utenti con ruoli e aziende diverse
            Utente produttore = new Produttore("Mario", "Rossi", "mario.rossi@example.com", passwordEncoder.encode("password123"), azienda1);
            Utente trasformatore = new Trasformatore("Giovanni", "Verdi", "giovanni.verdi@example.com", passwordEncoder.encode("password456"),  azienda2);
            Utente distributore = new Distributore("Anna", "Neri", "anna.neri@example.com", passwordEncoder.encode("password789"),  azienda1);
            Utente curatore = new Curatore("Luca", "Bianchi", "luca.bianchi@example.com", passwordEncoder.encode("password321"),  azienda2);
            Utente gestore = new Gestore("Elena", "Blu", "elena.blu@example.com", passwordEncoder.encode("password987"), azienda2);

            utenteRepository.saveAll(List.of(produttore, trasformatore, distributore, curatore, gestore));
            System.out.println("👤 Utenti creati e associati alle aziende.");

            System.out.println("✅ Database inizializzato con dati di esempio.");
        } else {
            System.out.println("⚠️ Database già inizializzato, nessuna modifica effettuata.");
        }
    }
}

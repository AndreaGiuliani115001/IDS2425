package it.unicam.IDS2425.service;

import it.unicam.IDS2425.model.Azienda;
import it.unicam.IDS2425.service.factory.UtenteFactory;
import it.unicam.IDS2425.model.Ruolo;
import it.unicam.IDS2425.model.Utente;
import it.unicam.IDS2425.repository.UtenteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final UtenteFactory utenteFactory;
    private final PasswordEncoder passwordEncoder;

    public UtenteService(UtenteRepository utenteRepository, UtenteFactory utenteFactory, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.utenteFactory = utenteFactory;
        this.passwordEncoder = passwordEncoder;
    }

    public Utente registraUtente(String nome, String cognome, String email, String password, Ruolo ruolo, Azienda azienda) {
        String hashedPassword = passwordEncoder.encode(password);
        Utente nuovoUtente = utenteFactory.creaUtente(nome, cognome, email, hashedPassword, ruolo,azienda);
        return utenteRepository.save(nuovoUtente);
    }

    public Utente verificaCredenziali(String email, String password) {
        Optional<Utente> optionalUtente = utenteRepository.findByEmail(email);
        if (optionalUtente.isPresent()) {
            Utente utente = optionalUtente.get();
            if (passwordEncoder.matches(password, utente.getPassword())) {
                return utente;
            }
        }
        return null;  // Credenziali non valide
    }

    public Optional<Utente> trovaUtenteByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }
}

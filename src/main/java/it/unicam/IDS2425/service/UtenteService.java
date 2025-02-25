package it.unicam.IDS2425.service;

import it.unicam.IDS2425.model.Azienda;
import it.unicam.IDS2425.factory.UtenteFactory;
import it.unicam.IDS2425.model.Prodotto;
import it.unicam.IDS2425.model.Ruolo;
import it.unicam.IDS2425.model.Utente;
import it.unicam.IDS2425.repository.UtenteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servizio che gestisce le operazioni relative agli utenti.
 * Include metodi per registrare un nuovo utente, verificare le credenziali,
 * cercare utenti per email e salvare modifiche agli utenti.
 */
@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final UtenteFactory utenteFactory;
    private final PasswordEncoder passwordEncoder;

    /**
     * Restituisce l'intera lista degli utenti registrati a sistema.
     *
     * @return La lista degli utenti.
     */
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    /**
     * Costruttore per iniettare i dipendenti necessari per la gestione degli utenti.
     *
     * @param utenteRepository Il repository per l'interazione con gli utenti nel database.
     * @param utenteFactory La factory per la creazione degli utenti.
     * @param passwordEncoder L'encoder per la gestione delle password.
     */
    public UtenteService(UtenteRepository utenteRepository, UtenteFactory utenteFactory, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.utenteFactory = utenteFactory;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registra un nuovo utente nel sistema, codificando la password e creando un'istanza di {@link Utente}.
     *
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param email L'email dell'utente.
     * @param password La password dell'utente.
     * @param ruolo Il ruolo dell'utente (ad esempio, produttore, curatore, ecc.).
     * @param azienda L'azienda associata all'utente.
     * @return Il nuovo utente salvato nel database.
     */
    public Utente registraUtente(String nome, String cognome, String email, String password, Ruolo ruolo, Azienda azienda) {
        String hashedPassword = passwordEncoder.encode(password);
        Utente nuovoUtente = utenteFactory.creaUtente(nome, cognome, email, hashedPassword, ruolo, azienda);
        return utenteRepository.save(nuovoUtente);
    }

    /**
     * Verifica le credenziali dell'utente confrontando l'email e la password fornita.
     *
     * @param email L'email dell'utente.
     * @param password La password dell'utente.
     * @return L'utente se le credenziali sono valide, altrimenti {@code null}.
     */
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

    /**
     * Trova un utente nel database utilizzando la sua email.
     *
     * @param email L'email dell'utente da cercare.
     * @return Un {@link Optional} contenente l'utente trovato, se esistente.
     */
    public Optional<Utente> trovaUtenteByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    /**
     * Salva un utente nel database.
     *
     * @param utente L'utente da salvare.
     * @return L'utente salvato.
     */
    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }
}

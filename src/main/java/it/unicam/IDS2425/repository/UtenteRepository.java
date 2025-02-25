package it.unicam.IDS2425.repository;

import it.unicam.IDS2425.model.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

/**
 * Repository per la gestione delle operazioni CRUD sulla collezione degli utenti.
 * Fornisce metodi per la ricerca degli utenti per email e nome.
 */
public interface UtenteRepository extends MongoRepository<Utente, String> {

    /**
     * Trova un utente tramite l'email.
     *
     * @param email L'email dell'utente.
     * @return Un Optional contenente l'utente se trovato, altrimenti vuoto.
     */
    Optional<Utente> findByEmail(String email);

    /**
     * Trova un utente tramite il nome.
     *
     * @param username Il nome dell'utente.
     * @return Un Optional contenente l'utente se trovato, altrimenti vuoto.
     */
    Optional<Utente> findByNome(String username);
}
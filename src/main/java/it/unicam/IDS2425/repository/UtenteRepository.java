package it.unicam.IDS2425.repository;

import it.unicam.IDS2425.model.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UtenteRepository extends MongoRepository<Utente, String> {
    Optional<Utente> findByEmail(String email);
}

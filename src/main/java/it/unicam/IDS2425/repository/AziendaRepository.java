package it.unicam.IDS2425.repository;

import it.unicam.IDS2425.model.Azienda;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AziendaRepository extends MongoRepository<Azienda, String> {
}

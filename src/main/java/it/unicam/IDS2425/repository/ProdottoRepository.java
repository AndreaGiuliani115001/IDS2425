package it.unicam.IDS2425.repository;

import it.unicam.IDS2425.model.Prodotto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends MongoRepository<Prodotto, String> {
}

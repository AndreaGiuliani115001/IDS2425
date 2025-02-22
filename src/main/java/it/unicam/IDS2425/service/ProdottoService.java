package it.unicam.IDS2425.service;

import it.unicam.IDS2425.dto.ProdottoRequestDTO;
import it.unicam.IDS2425.model.*;
import it.unicam.IDS2425.repository.ProdottoRepository;
import it.unicam.IDS2425.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdottoService {

    private final ProdottoRepository prodottoRepository;
    private final UtenteRepository utenteRepository;

    public ResponseEntity<?> creaProdotto(String email, ProdottoRequestDTO prodottoDTO) {
        Optional<Utente> utenteOpt = utenteRepository.findByEmail(email);
        if (utenteOpt.isEmpty()) {
            return ResponseEntity.status(403).body("Utente non trovato");
        }

        Utente utente = utenteOpt.get();
        if (!(utente instanceof Produttore)) {
            return ResponseEntity.status(403).body("Solo i produttori possono creare nuovi prodotti.");
        }

        Prodotto prodotto = new Prodotto(
                prodottoDTO.getNome(),
                prodottoDTO.getDescrizione(),
                prodottoDTO.getCategoria(),
                prodottoDTO.getQuantita(),
                utente.getAzienda()
        );

        Prodotto prodottoCreato = prodottoRepository.save(prodotto);
        return ResponseEntity.ok(prodottoCreato);
    }

    public ResponseEntity<?> trasformaProdotto(String email, String prodottoOriginaleId, ProdottoRequestDTO prodottoDTO) {
        Optional<Utente> utenteOpt = utenteRepository.findByEmail(email);
        if (utenteOpt.isEmpty()) {
            return ResponseEntity.status(403).body("Utente non trovato.");
        }

        Utente utente = utenteOpt.get();
        if (!(utente instanceof Trasformatore)) {
            return ResponseEntity.status(403).body("Solo i trasformatori possono trasformare i prodotti.");
        }

        Optional<Prodotto> originaleOpt = prodottoRepository.findById(prodottoOriginaleId);
        if (originaleOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Prodotto originale non trovato.");
        }

        Prodotto originale = originaleOpt.get();

        if (originale.getStatoProdotto() != StatoProdotto.VALIDATO) {
            return ResponseEntity.status(400).body("Il prodotto deve essere validato prima di essere trasformato.");
        }

        Prodotto prodottoTrasformato = new Prodotto(
                prodottoDTO.getNome(),
                prodottoDTO.getDescrizione(),
                prodottoDTO.getCategoria(),
                prodottoDTO.getQuantita(),
                utente.getAzienda()
        );
        prodottoTrasformato.setTrasformato(true);
        prodottoTrasformato.setProdottoOriginale(originale);
        prodottoTrasformato.setStatoProdotto(StatoProdotto.IN_CREAZIONE);

        Prodotto prodottoCreato = prodottoRepository.save(prodottoTrasformato);

        return ResponseEntity.ok(prodottoCreato);
    }


    public ResponseEntity<?> validaProdotto(String prodottoId, String email) {
        Optional<Utente> utenteOpt = utenteRepository.findByEmail(email);
        if (utenteOpt.isEmpty()) {
            return ResponseEntity.status(403).body("Utente non trovato.");
        }

        Utente utente = utenteOpt.get();
        if (!(utente instanceof Curatore)) {
            return ResponseEntity.status(403).body("Solo i curatori possono validare i prodotti.");
        }

        Optional<Prodotto> prodottoOpt = prodottoRepository.findById(prodottoId);
        if (prodottoOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Prodotto non trovato.");
        }

        Prodotto prodotto = prodottoOpt.get();

        if (prodotto.getStatoProdotto() != StatoProdotto.IN_CREAZIONE) {
            return ResponseEntity.status(400).body("Il prodotto non è in fase di creazione e non può essere validato.");
        }

        prodotto.setStatoProdotto(StatoProdotto.VALIDATO);
        prodottoRepository.save(prodotto);

        return ResponseEntity.ok("Prodotto validato con successo.");
    }


    private Azienda getAziendaUtente(String email) {
        return utenteRepository.findByEmail(email)
                .map(Utente::getAzienda)
                .orElseThrow(() -> new IllegalStateException("L'utente non ha un'azienda associata."));
    }

}

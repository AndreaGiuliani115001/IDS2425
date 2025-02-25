package it.unicam.IDS2425.service;

import it.unicam.IDS2425.dto.ProdottoRequestDTO;
import it.unicam.IDS2425.model.*;
import it.unicam.IDS2425.repository.ProdottoRepository;
import it.unicam.IDS2425.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servizio che gestisce le operazioni relative ai prodotti.
 * Include metodi per creare, trasformare e validare i prodotti.
 * L'accesso a queste operazioni è regolato in base ai ruoli degli utenti.
 */
@Service
@RequiredArgsConstructor
public class ProdottoService {

    private final ProdottoRepository prodottoRepository;
    private final UtenteRepository utenteRepository;

    /**
     * Restituisce l'intera lista dei prodotti caricati a sistema.
     *
     * @return La lista dei prodotti.
     */
    public List<Prodotto> getAllProdotti() {
        return prodottoRepository.findAll();
    }


    /**
     * Crea un nuovo prodotto associato al produttore specificato dall'email.
     * Solo gli utenti di tipo {@link Produttore} possono creare nuovi prodotti.
     *
     * @param email L'email dell'utente che sta creando il prodotto.
     * @param prodottoDTO I dati del prodotto da creare.
     * @return La risposta HTTP con il prodotto creato o un errore.
     */
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
                prodottoDTO.getPrezzo(),
                utente.getAzienda()
        );

        Prodotto prodottoCreato = prodottoRepository.save(prodotto);
        return ResponseEntity.ok(prodottoCreato);
    }

    /**
     * Trasforma un prodotto esistente. Solo gli utenti di tipo {@link Trasformatore} possono trasformare i prodotti.
     * Il prodotto deve essere stato validato prima di essere trasformato.
     *
     * @param email L'email dell'utente che sta trasformando il prodotto.
     * @param prodottoOriginaleId L'ID del prodotto originale da trasformare.
     * @param prodottoDTO I dati del nuovo prodotto trasformato.
     * @return La risposta HTTP con il prodotto trasformato o un errore.
     */
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
                prodottoDTO.getPrezzo(),
                utente.getAzienda()
        );
        prodottoTrasformato.setTrasformato(true);
        prodottoTrasformato.setProdottoOriginale(originale);
        prodottoTrasformato.setStatoProdotto(StatoProdotto.IN_CREAZIONE);

        Prodotto prodottoCreato = prodottoRepository.save(prodottoTrasformato);

        return ResponseEntity.ok(prodottoCreato);
    }

    /**
     * Valida un prodotto. Solo gli utenti di tipo {@link Curatore} possono validare i prodotti.
     * Il prodotto deve essere in stato di creazione per poter essere validato.
     *
     * @param prodottoId L'ID del prodotto da validare.
     * @param email L'email dell'utente che sta validando il prodotto.
     * @return La risposta HTTP con il risultato della validazione o un errore.
     */
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

    /**
     * Restituisce l'azienda associata all'utente identificato dalla sua email.
     *
     * @param email L'email dell'utente per recuperare l'azienda.
     * @return L'azienda associata all'utente.
     * @throws IllegalStateException Se l'utente non ha un'azienda associata.
     */
    private Azienda getAziendaUtente(String email) {
        return utenteRepository.findByEmail(email)
                .map(Utente::getAzienda)
                .orElseThrow(() -> new IllegalStateException("L'utente non ha un'azienda associata."));
    }

}

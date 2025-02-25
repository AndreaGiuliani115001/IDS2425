package it.unicam.IDS2425.controller;

import it.unicam.IDS2425.dto.ProdottoRequestDTO;
import it.unicam.IDS2425.model.Prodotto;
import it.unicam.IDS2425.security.JwtTokenProvider;
import it.unicam.IDS2425.service.ProdottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione delle operazioni relative ai prodotti.
 * Fornisce endpoint per la creazione, trasformazione e validazione dei prodotti.
 */
@RestController
@RequestMapping("/api/prodotti")
@RequiredArgsConstructor
public class ProdottoController {

    private final ProdottoService prodottoService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Restituisce tutti i prodotti creati.
     * Accessibile a tutti gli utenti (pubblico).
     *
     * @return Una ResponseEntity con la lista dei prodotti.
     */
    @GetMapping("/")
    public ResponseEntity<?> getAllProdotti() {
        List<Prodotto> prodotti = prodottoService.getAllProdotti();
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Crea un nuovo prodotto. Accessibile solo ai produttori.
     *
     * @param token Il token JWT dell'utente autenticato.
     * @param prodottoDTO I dati del prodotto da creare.
     * @return Una ResponseEntity con l'esito dell'operazione.
     */
    @PostMapping("/crea")
    public ResponseEntity<?> creaProdotto(@RequestHeader("Authorization") String token,
                                          @RequestBody ProdottoRequestDTO prodottoDTO) {
        String email = jwtTokenProvider.getUsernameFromToken(token.replace("Bearer ", ""));
        return prodottoService.creaProdotto(email, prodottoDTO);
    }

    /**
     * Trasforma un prodotto esistente in un prodotto derivato. Accessibile solo ai trasformatori.
     *
     * @param token Il token JWT dell'utente autenticato.
     * @param prodottoOriginaleId L'ID del prodotto originale da trasformare.
     * @param prodottoDTO I dati del nuovo prodotto trasformato.
     * @return Una ResponseEntity con l'esito dell'operazione.
     */
    @PostMapping("/trasforma")
    public ResponseEntity<?> trasformaProdotto(@RequestHeader("Authorization") String token,
                                               @RequestParam String prodottoOriginaleId,
                                               @RequestBody ProdottoRequestDTO prodottoDTO) {
        String email = jwtTokenProvider.getUsernameFromToken(token.replace("Bearer ", ""));
        return prodottoService.trasformaProdotto(email, prodottoOriginaleId, prodottoDTO);
    }

    /**
     * Valida un prodotto esistente.
     *
     * @param token Il token JWT dell'utente autenticato.
     * @param id L'ID del prodotto da validare.
     * @return Una ResponseEntity con l'esito dell'operazione.
     */
    @PutMapping("/valida/{id}")
    public ResponseEntity<?> validaProdotto(@RequestHeader("Authorization") String token, @PathVariable String id) {
        String email = jwtTokenProvider.getUsernameFromToken(token.replace("Bearer ", ""));
        return prodottoService.validaProdotto(id, email);
    }
}

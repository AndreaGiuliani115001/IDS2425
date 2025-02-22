package it.unicam.IDS2425.controller;

import it.unicam.IDS2425.dto.ProdottoRequestDTO;
import it.unicam.IDS2425.security.JwtTokenProvider;
import it.unicam.IDS2425.service.ProdottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prodotti")
@RequiredArgsConstructor
public class ProdottoController {

    private final ProdottoService prodottoService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Endpoint per la creazione di un prodotto (solo per PRODUTTORE).
     */
    @PostMapping("/crea")
    public ResponseEntity<?> creaProdotto(@RequestHeader("Authorization") String token,
                                          @RequestBody ProdottoRequestDTO prodottoDTO) {
        String email = jwtTokenProvider.getUsernameFromToken(token.replace("Bearer ", ""));
        return prodottoService.creaProdotto(email, prodottoDTO);
    }

    /**
     * Endpoint per la creazione di un prodotto trasformato (solo per TRASFORMATORE).
     */
    @PostMapping("/trasforma")
    public ResponseEntity<?> trasformaProdotto(@RequestHeader("Authorization") String token,
                                               @RequestParam String prodottoOriginaleId,
                                               @RequestBody ProdottoRequestDTO prodottoDTO) {
        String email = jwtTokenProvider.getUsernameFromToken(token.replace("Bearer ", ""));
        return prodottoService.trasformaProdotto(email, prodottoOriginaleId, prodottoDTO);
    }

    /**
     * Endpoint per validare un prodotto.
     */
    @PutMapping("/valida/{id}")
    public ResponseEntity<?> validaProdotto(@RequestHeader("Authorization") String token, @PathVariable String id) {
        String email = jwtTokenProvider.getUsernameFromToken(token.replace("Bearer ", ""));
        return prodottoService.validaProdotto(id, email);
    }


}

package it.unicam.IDS2425.controller;

import it.unicam.IDS2425.dto.LoginRequestDTO;
import it.unicam.IDS2425.dto.UtenteRegistrazioneDTO;
import it.unicam.IDS2425.model.Azienda;
import it.unicam.IDS2425.model.Prodotto;
import it.unicam.IDS2425.model.Ruolo;
import it.unicam.IDS2425.model.Utente;
import it.unicam.IDS2425.repository.AziendaRepository;
import it.unicam.IDS2425.service.UtenteService;
import it.unicam.IDS2425.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller per la gestione degli utenti.
 * Fornisce endpoint per la registrazione, autenticazione e test del token.
 */
@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    private final UtenteService utenteService;
    private final AziendaRepository aziendaRepository;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Costruttore per UtenteController.
     *
     * @param utenteService Il servizio per la gestione degli utenti.
     * @param aziendaRepository Il repository per la gestione delle aziende.
     * @param jwtTokenProvider Il provider per la gestione dei token JWT.
     */
    public UtenteController(UtenteService utenteService, AziendaRepository aziendaRepository, JwtTokenProvider jwtTokenProvider) {
        this.utenteService = utenteService;
        this.aziendaRepository = aziendaRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Restituisce tutti gli utenti registrati a sistema.
     * Accessibile solo al gestore.
     *
     * @return Una ResponseEntity con la lista degli utenti.
     */
    @GetMapping("/")
    public ResponseEntity<?> getAllUtenti() {
        List<Utente> utenti = utenteService.getAllUtenti();
        return ResponseEntity.ok(utenti);
    }

    /**
     * Registra un nuovo utente.
     *
     * @param dto DTO contenente i dati dell'utente da registrare.
     * @return ResponseEntity con il risultato dell'operazione.
     */
    @PostMapping("/registrazione")
    public ResponseEntity<String> registrazione(@RequestBody UtenteRegistrazioneDTO dto) {
        Ruolo ruolo = Ruolo.valueOf(dto.getRuolo().toUpperCase());
        Optional<Azienda> aziendaOpt = aziendaRepository.findById(dto.getAziendaId());
        if (aziendaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Errore: Azienda non trovata.");
        }
        Azienda azienda = aziendaOpt.get();
        Utente utente = utenteService.registraUtente(
                dto.getNome(),
                dto.getCognome(),
                dto.getEmail(),
                dto.getPassword(),
                ruolo,
                azienda
        );
        return ResponseEntity.ok("Utente registrato con successo.");
    }

    /**
     * Effettua il login di un utente.
     *
     * @param loginRequest DTO contenente le credenziali di accesso.
     * @return ResponseEntity con il token JWT o un errore di autenticazione.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDTO loginRequest) {
        Utente utente = utenteService.verificaCredenziali(loginRequest.getEmail(), loginRequest.getPassword());
        if (utente != null) {
            String token = jwtTokenProvider.generateToken(utente.getEmail(), utente.getRuolo().name());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(Map.of("error", "Credenziali non valide"));
    }

    /**
     * Verifica un token JWT e restituisce le informazioni dell'utente autenticato.
     *
     * @param token Il token JWT da verificare.
     * @return ResponseEntity contenente email e ruolo dell'utente autenticato.
     */
    @PostMapping("/test-token")
    public ResponseEntity<?> testToken(@RequestHeader("Authorization") String token) {
        String email = jwtTokenProvider.getUsernameFromToken(token.replace("Bearer ", ""));
        String ruolo = jwtTokenProvider.getRuoloFromToken(token.replace("Bearer ", ""));
        return ResponseEntity.ok("Email: " + email + ", Ruolo: " + ruolo);
    }
}

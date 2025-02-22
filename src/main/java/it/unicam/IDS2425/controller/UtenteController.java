package it.unicam.IDS2425.controller;

import it.unicam.IDS2425.dto.LoginRequestDTO;
import it.unicam.IDS2425.dto.UtenteRegistrazioneDTO;
import it.unicam.IDS2425.model.Azienda;
import it.unicam.IDS2425.model.Ruolo;
import it.unicam.IDS2425.model.Utente;
import it.unicam.IDS2425.repository.AziendaRepository;
import it.unicam.IDS2425.service.UtenteService;
import it.unicam.IDS2425.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    private final UtenteService utenteService;
    private final AziendaRepository aziendaRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UtenteController(UtenteService utenteService, AziendaRepository aziendaRepository, JwtTokenProvider jwtTokenProvider) {
        this.utenteService = utenteService;
        this.aziendaRepository = aziendaRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/registrazione")
    public ResponseEntity<String> registrazione(@RequestBody UtenteRegistrazioneDTO dto) {

        // Converte la stringa del ruolo in un valore dell'enum Ruolo
        Ruolo ruolo = Ruolo.valueOf(dto.getRuolo().toUpperCase());

        // Recupera l'azienda dal database
        Optional<Azienda> aziendaOpt = aziendaRepository.findById(dto.getAziendaId());
        if (aziendaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Errore: Azienda non trovata.");
        }
        Azienda azienda = aziendaOpt.get();

        // Crea l’oggetto utente specifico tramite il factory
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

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDTO loginRequest) {
        Utente utente = utenteService.verificaCredenziali(loginRequest.getEmail(), loginRequest.getPassword());

        if (utente != null) {
            // Genera il token con email + ruolo
            String token = jwtTokenProvider.generateToken(utente.getEmail(), utente.getRuolo().name());

            // Creiamo la risposta con il token
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body(Map.of("error", "Credenziali non valide"));
    }


    /**
     * Endpoint per testare il token.
     */
    @PostMapping("/test-token")
    public ResponseEntity<?> testToken(@RequestHeader("Authorization") String token) {
        String email = jwtTokenProvider.getUsernameFromToken(token.replace("Bearer ", ""));
        String ruolo = jwtTokenProvider.getRuoloFromToken(token.replace("Bearer ", ""));

        return ResponseEntity.ok("Email: " + email + ", Ruolo: " + ruolo);
    }

}

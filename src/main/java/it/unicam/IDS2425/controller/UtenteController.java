package it.unicam.IDS2425.controller;

import it.unicam.IDS2425.dto.LoginRequestDTO;
import it.unicam.IDS2425.dto.UtenteRegistrazioneDTO;
import it.unicam.IDS2425.model.Ruolo;
import it.unicam.IDS2425.model.Utente;
import it.unicam.IDS2425.security.JwtTokenProvider;
import it.unicam.IDS2425.service.UtenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    private final UtenteService utenteService;
    private final JwtTokenProvider jwtTokenProvider;

    public UtenteController(UtenteService utenteService, JwtTokenProvider jwtTokenProvider) {
        this.utenteService = utenteService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/registrazione")
    public ResponseEntity<String> registrazione(@RequestBody UtenteRegistrazioneDTO dto) {

        // Converte la stringa del ruolo in un valore dell'enum Ruolo
        Ruolo ruolo = Ruolo.valueOf(dto.getRuolo().toUpperCase());

        // Crea l’oggetto utente specifico tramite il factory
        Utente utente = utenteService.registraUtente(
                dto.getNome(),
                dto.getCognome(),
                dto.getEmail(),
                dto.getPassword(),
                ruolo
        );

        return ResponseEntity.ok("Utente registrato con successo.");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDTO loginRequest) {

        Utente utente = utenteService.verificaCredenziali(loginRequest.getEmail(), loginRequest.getPassword());
        if (utente != null) {
            String token = jwtTokenProvider.generateToken(utente.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(Map.of("error", "Credenziali non valide"));
    }
}

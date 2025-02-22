package it.unicam.IDS2425.security;

import it.unicam.IDS2425.model.Utente;
import it.unicam.IDS2425.repository.UtenteRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final UtenteRepository utenteRepository;

    public JwtUtil(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    /**
     * Ritorna l'utente autenticato basandosi sul JWT.
     */
    public Utente getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return utenteRepository.findByNome(username)
                    .orElseThrow(() -> new IllegalStateException("Utente non trovato"));
        } else {
            throw new IllegalStateException("Autenticazione non valida");
        }
    }
}

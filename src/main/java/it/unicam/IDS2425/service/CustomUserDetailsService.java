package it.unicam.IDS2425.service;

import it.unicam.IDS2425.model.Utente;
import it.unicam.IDS2425.repository.UtenteRepository;
import it.unicam.IDS2425.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servizio che implementa l'interfaccia {@link UserDetailsService} di Spring Security.
 * Questo servizio carica i dettagli dell'utente in base all'email e restituisce un oggetto
 * {@link CustomUserDetails} per gestire l'autenticazione e l'autorizzazione.
 */
@Service  // Rendi questa classe un bean gestito da Spring
public class CustomUserDetailsService implements UserDetailsService {

    private final UtenteRepository utenteRepository;

    /**
     * Costruttore per iniettare il repository degli utenti.
     *
     * @param utenteRepository Il repository per l'interazione con gli utenti nel database.
     */
    public CustomUserDetailsService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    /**
     * Carica un utente in base all'email fornita e restituisce un oggetto {@link CustomUserDetails}.
     * Se l'utente non viene trovato, viene lanciata un'eccezione {@link UsernameNotFoundException}.
     *
     * @param email L'email dell'utente da cercare nel database.
     * @return L'oggetto {@link UserDetails} che rappresenta l'utente trovato.
     * @throws UsernameNotFoundException Se l'utente con l'email specificata non viene trovato.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con email: " + email));
        return new CustomUserDetails(utente);
    }
}

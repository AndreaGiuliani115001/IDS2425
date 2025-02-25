package it.unicam.IDS2425.security;

import it.unicam.IDS2425.model.Utente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Implementazione personalizzata di UserDetails per la gestione dell'autenticazione degli utenti.
 */
public class CustomUserDetails implements UserDetails {

    private final Utente utente;

    /**
     * Costruttore per inizializzare un oggetto CustomUserDetails con un utente.
     *
     * @param utente L'utente autenticato.
     */
    public CustomUserDetails(Utente utente) {
        this.utente = utente;
    }

    /**
     * Restituisce l'insieme delle autorizzazioni (ruoli) dell'utente.
     *
     * @return Lista di autorità con il ruolo dell'utente.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + utente.getRuolo().name()));
    }

    /**
     * Restituisce la password dell'utente.
     *
     * @return La password dell'utente.
     */
    @Override
    public String getPassword() {
        return utente.getPassword();
    }

    /**
     * Restituisce l'email dell'utente come nome utente.
     *
     * @return L'email dell'utente.
     */
    @Override
    public String getUsername() {
        return utente.getEmail();
    }

    /**
     * Indica se l'account è scaduto.
     *
     * @return true se l'account non è scaduto.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica se l'account è bloccato.
     *
     * @return true se l'account non è bloccato.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica se le credenziali dell'utente sono scadute.
     *
     * @return true se le credenziali non sono scadute.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica se l'utente è abilitato.
     *
     * @return true se l'utente è abilitato.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
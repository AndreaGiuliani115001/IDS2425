package it.unicam.IDS2425.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro per l'autenticazione basata su JWT.
 * Intercetta le richieste HTTP, estrae il token JWT e autentica l'utente se il token è valido.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    /**
     * Costruttore per inizializzare il filtro con il provider di token e il servizio di gestione utenti.
     *
     * @param jwtTokenProvider Il provider di token JWT.
     * @param userDetailsService Il servizio per il recupero dei dettagli dell'utente.
     */
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Metodo che intercetta ogni richiesta HTTP e gestisce l'autenticazione tramite JWT.
     *
     * @param request La richiesta HTTP.
     * @param response La risposta HTTP.
     * @param filterChain La catena di filtri.
     * @throws ServletException Se si verifica un errore durante la gestione della richiesta.
     * @throws IOException Se si verifica un errore di input/output.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getTokenFromRequest(request);

        if (token != null && jwtTokenProvider.validateToken(token, jwtTokenProvider.getUsernameFromToken(token))) {
            String email = jwtTokenProvider.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Estrae il token JWT dall'header Authorization della richiesta HTTP.
     *
     * @param request La richiesta HTTP.
     * @return Il token JWT se presente, altrimenti null.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
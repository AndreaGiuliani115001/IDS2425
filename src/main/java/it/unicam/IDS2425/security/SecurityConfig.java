package it.unicam.IDS2425.security;

import it.unicam.IDS2425.exception.CustomAccessDeniedHandler;
import it.unicam.IDS2425.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

/**
 * Configurazione della sicurezza per l'applicazione.
 * Definisce la gestione dell'autenticazione, autorizzazione e protezione delle richieste HTTP.
 */
@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    /**
     * Costruttore che inizializza i servizi di sicurezza.
     *
     * @param jwtTokenProvider Provider per la gestione dei token JWT.
     * @param userDetailsService Servizio per il recupero dei dettagli dell'utente.
     * @param accessDeniedHandler Gestore per gli accessi negati.
     */
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService userDetailsService, CustomAccessDeniedHandler accessDeniedHandler) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.accessDeniedHandler = new CustomAccessDeniedHandler();
    }

    /**
     * Configura l'encoder per la gestione delle password.
     *
     * @return Un'istanza di BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura la catena di filtri di sicurezza.
     *
     * @param http Configurazione di sicurezza HTTP.
     * @return L'oggetto SecurityFilterChain configurato.
     * @throws Exception Se si verifica un errore durante la configurazione.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/utenti/registrazione", "/api/utenti/login", "/api/prodotti/").permitAll()
                        .requestMatchers("/api/prodotti/crea").hasRole("PRODUTTORE")
                        .requestMatchers("/api/prodotti/trasforma").hasRole("TRASFORMATORE")
                        .requestMatchers("/api/prodotti/valida/**").hasRole("CURATORE")
                        .requestMatchers("/api/utenti/").hasRole("GESTORE")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new Http403ForbiddenEntryPoint()) // Per 403 non autenticati
                        .accessDeniedHandler(accessDeniedHandler) // Per 403 senza permessi
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable);
        return http.build();
    }

    /**
     * Configura il gestore di autenticazione.
     *
     * @param authenticationConfiguration Configurazione dell'autenticazione.
     * @return Il gestore di autenticazione.
     * @throws Exception Se si verifica un errore durante la configurazione.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
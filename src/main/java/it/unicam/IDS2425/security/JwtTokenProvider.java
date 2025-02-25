package it.unicam.IDS2425.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * Classe per la gestione dei token JWT.
 * Fornisce metodi per la generazione, estrazione e validazione dei token.
 */
@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long jwtExpirationMs;

    /**
     * Costruttore che inizializza il provider con la chiave segreta e la durata del token.
     *
     * @param secret Chiave segreta per la firma del token.
     * @param jwtExpirationMs Durata di validità del token in millisecondi.
     */
    public JwtTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long jwtExpirationMs) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.jwtExpirationMs = jwtExpirationMs;
    }

    /**
     * Genera un token JWT per un utente specifico.
     *
     * @param username Nome utente associato al token.
     * @param ruolo Ruolo dell'utente.
     * @return Il token JWT generato.
     */
    public String generateToken(String username, String ruolo) {
        return Jwts.builder()
                .setSubject(username)
                .claim("ruolo", "ROLE_" + ruolo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Estrae il nome utente da un token JWT.
     *
     * @param token Il token JWT.
     * @return Il nome utente estratto dal token.
     */
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Estrae il ruolo dell'utente dal token JWT.
     *
     * @param token Il token JWT.
     * @return Il ruolo dell'utente.
     */
    public String getRuoloFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("ruolo");
    }

    /**
     * Verifica se un token JWT è valido.
     *
     * @param token Il token JWT da validare.
     * @param username Il nome utente da confrontare con quello contenuto nel token.
     * @return true se il token è valido, false altrimenti.
     */
    public boolean validateToken(String token, String username) {
        final String tokenUsername = getUsernameFromToken(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * Estrae un claim specifico da un token JWT.
     *
     * @param token Il token JWT.
     * @param claimsResolver Funzione per risolvere il claim desiderato.
     * @param <T> Tipo del valore restituito.
     * @return Il valore del claim estratto.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Estrae tutti i claims da un token JWT.
     *
     * @param token Il token JWT.
     * @return I claims contenuti nel token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Verifica se un token JWT è scaduto.
     *
     * @param token Il token JWT da verificare.
     * @return true se il token è scaduto, false altrimenti.
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
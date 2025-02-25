package it.unicam.IDS2425.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestore personalizzato per gli accessi negati.
 * Intercetta le richieste non autorizzate e restituisce una risposta JSON dettagliata.
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Gestisce l'eccezione di accesso negato restituendo una risposta JSON con dettagli sull'errore.
     *
     * @param request La richiesta HTTP in corso.
     * @param response La risposta HTTP da restituire.
     * @param accessDeniedException L'eccezione di accesso negato intercettata.
     * @throws IOException In caso di errore nella scrittura della risposta.
     * @throws ServletException In caso di errore generico nella gestione della richiesta.
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("error", "Accesso negato");
        errorDetails.put("message", "Non hai i permessi per accedere a questa risorsa.");
        errorDetails.put("status", HttpServletResponse.SC_FORBIDDEN);
        errorDetails.put("path", request.getRequestURI());

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(errorDetails));
    }
}
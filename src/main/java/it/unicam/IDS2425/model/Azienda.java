package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "aziende")
public class Azienda {

    @Id
    private String id;

    private String ragioneSociale;
    private String pIva;
    private String indirizzo;
    private String email;
    private String numeroTelefono;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Azienda(String ragioneSociale, String pIva, String indirizzo, String email, String numeroTelefono) {
        this.ragioneSociale = ragioneSociale;
        this.pIva = pIva;
        this.indirizzo = indirizzo;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
    }
}

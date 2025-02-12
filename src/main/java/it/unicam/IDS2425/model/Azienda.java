package it.unicam.IDS2425.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "aziende")
public class Azienda {

    @Id
    private String id;  // Identificatore del documento

    private String ragioneSociale;
    private String pIva;
    private String indirizzo;
    private String email;
    private String numeroTelefono;

    @DBRef
    private List<Utente> dipendenti;

    @DBRef
    private List<PacchettoProdotti> pacchettiProdotti;

    @DBRef
    private List<Prodotto> prodotti;  // Relazione con i prodotti

    public Azienda(String ragioneSociale, String pIva, String indirizzo, String email, String numeroTelefono) {
        this.ragioneSociale = ragioneSociale;
        this.pIva = pIva;
        this.indirizzo = indirizzo;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
        this.dipendenti = new ArrayList<>();
        this.prodotti = new ArrayList<>();
    }
}

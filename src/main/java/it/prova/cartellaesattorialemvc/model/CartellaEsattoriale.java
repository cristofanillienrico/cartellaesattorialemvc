package it.prova.cartellaesattorialemvc.model;


//CartellaEsattoriale (id,descrizione,importo,stato,contribuente).
// Ovviamente nel Contribuente ci sarà anche la lista di Cartelle.

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "cartella_esattoriale")
public class CartellaEsattoriale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "{descrizione.notblank}")
    @Column(name = "descrizione")
    private String descrizione;

    @NotNull(message = "{importo.notnull}")
    @Min(1)
    @Column(name = "importo")
    private Double importo;

    @NotNull(message = "{stato.notblank}")
    @Column(name = "stato")
    @Enumerated(EnumType.STRING)
    private Stato stato;

    @NotNull(message = "{contribuente.notnull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contribuente_id")
    private Contribuente contribuente;


    public CartellaEsattoriale() {
    }

    public CartellaEsattoriale(Long id, String descrizione, Double importo, Stato stato) {
        this.id = id;
        this.descrizione = descrizione;
        this.importo = importo;
        this.stato = stato;
    }

    public CartellaEsattoriale(String descrizione, Double importo, Stato stato) {
        this.descrizione = descrizione;
        this.importo = importo;
        this.stato = stato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Double getImporto() {
        return importo;
    }

    public void setImporto(Double importo) {
        this.importo = importo;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public Contribuente getContribuente() {
        return contribuente;
    }

    public void setContribuente(Contribuente contribuente) {
        this.contribuente = contribuente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartellaEsattoriale that = (CartellaEsattoriale) o;
        return Objects.equals(id, that.id) && Objects.equals(descrizione, that.descrizione) && Objects.equals(importo, that.importo) && stato == that.stato && Objects.equals(contribuente, that.contribuente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descrizione, importo, stato, contribuente);
    }

    @Override
    public String toString() {
        return "CartellaEsattoriale{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", importo=" + importo +
                ", stato=" + stato +
                '}';
    }
}





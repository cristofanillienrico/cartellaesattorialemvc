package it.prova.cartellaesattorialemvc.model;


//CartellaEsattoriale (id,descrizione,importo,stato,contribuente).
// Ovviamente nel Contribuente ci sarà anche la lista di Cartelle.

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}





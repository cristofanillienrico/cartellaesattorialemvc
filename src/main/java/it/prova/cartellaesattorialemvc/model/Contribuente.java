package it.prova.cartellaesattorialemvc.model;


//Contribuente (id,nome,cognome,data di nascita, codiceFiscale,indirizzo)


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "contribuente")
public class Contribuente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "{nome.notblank}")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "{cognome.notblank}")
    @Column(name = "cognome")
    private String cognome;

    @NotBlank(message = "{codiceFiscale.notblank}")
    @Size(min = 16, max = 16, message = "Il valore inserito  deve essere lungo {max} caratteri")
    @Column(name = "codiceFiscale")
    private String codiceFiscale;

    @NotNull(message = "{dataDiNascita.notnull}")
    @Column(name = "datadinascita")
    private Date dataDiNascita;

    @NotBlank(message = "{indirizzo.notblank}")
    @Column(name = "indirizzo")
    private String indirizzo;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contribuente")
    private Set<CartellaEsattoriale> cartelleEsattoriali = new HashSet<CartellaEsattoriale>(0);


    public Contribuente() {
    }

    public Contribuente(Long id, String nome, String cognome, String codiceFiscale, Date dataDiNascita, String indirizzo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataDiNascita = dataDiNascita;
        this.indirizzo = indirizzo;
    }

    public Contribuente(String nome, String cognome, String codiceFiscale, Date dataDiNascita, String indirizzo) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataDiNascita = dataDiNascita;
        this.indirizzo = indirizzo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Set<CartellaEsattoriale> getCartelleEsattoriali() {
        return cartelleEsattoriali;
    }

    public void setCartelleEsattoriali(Set<CartellaEsattoriale> cartelleEsattoriali) {
        this.cartelleEsattoriali = cartelleEsattoriali;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contribuente that = (Contribuente) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(cognome, that.cognome) && Objects.equals(codiceFiscale, that.codiceFiscale) && Objects.equals(dataDiNascita, that.dataDiNascita) && Objects.equals(indirizzo, that.indirizzo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome, codiceFiscale, dataDiNascita, indirizzo);
    }

    @Override
    public String toString() {
        return "Contribuente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}

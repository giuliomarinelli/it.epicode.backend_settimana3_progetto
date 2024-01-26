package it.epicode.week3.progetto.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(generator = "numero_tessera_seq")
    @SequenceGenerator(name = "numero_tessera_seq",
            sequenceName = "numero_tessera_seq", allocationSize = 1, schema = "public", initialValue = 1)
    @Column(name = "numero_di_tessera")
    private int numeroDiTessera;

    private String nome;
    private String cognome;
    @Column(name = "data_di_nascita")
    private LocalDate dataDiNascita;
    @OneToMany(mappedBy = "utente")
    List<Prestito> prestiti;

    public Utente() {}

    public Utente(int numeroDiTessera, String nome, String cognome, LocalDate dataDiNascita, List<Prestito> prestiti) {
        this.numeroDiTessera = numeroDiTessera;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.prestiti = prestiti;
    }

    public int getNumeroDiTessera() {
        return numeroDiTessera;
    }

    public void setNumeroDiTessera(int numeroDiTessera) {
        this.numeroDiTessera = numeroDiTessera;
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

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
    }

    @Override
    public String toString() {
        return  "numeroDiTessera=" + numeroDiTessera +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(numeroDiTessera, utente.numeroDiTessera);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDiTessera);
    }
}
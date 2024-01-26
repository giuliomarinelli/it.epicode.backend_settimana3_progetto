package it.epicode.week3.progetto.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FonteLeggibile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "isbn")
    private UUID ISBN;
    private String titolo;
    @Column(name = "anno_di_pubblicazione")
    private int annoDiPubblicazione;
    @Column(name = "numero_pagine")
    private int numeroPagine;
    @OneToOne(mappedBy = "elementoPrestato")
    private Prestito prestito;


    public FonteLeggibile() {}

    public FonteLeggibile(UUID ISBN, String titolo, int annoDiPubblicazione, int numeroPagine, Prestito prestito) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
        this.prestito = prestito;
    }

    public UUID getISBN() {
        return ISBN;
    }

    public void setISBN(UUID ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public Prestito getPrestito() {
        return prestito;
    }

    public void setPrestito(Prestito prestito) {
        this.prestito = prestito;
    }

    @Override
    public String toString() {
        return  "ISBN=" + ISBN +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine;

    }
}


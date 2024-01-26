package it.epicode.week3.progetto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "libri")
public class Libro extends FonteLeggibile {
    private String autore;
    private String genere;

    public Libro() {}

    public Libro(UUID ISBN, String titolo, int annoDiPubblicazione, int numeroPagine, Prestito prestito, String autore, String genere) {
        super(ISBN, titolo, annoDiPubblicazione, numeroPagine, prestito);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", autore='" + autore + '\'' +
                ", genere='" + genere + '\'';
    }
}

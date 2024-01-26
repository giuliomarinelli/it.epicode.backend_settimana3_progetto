package it.epicode.week3.progetto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "riviste")
public class Rivista extends FonteLeggibile {
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    public Rivista() {
    }

    public Rivista(UUID ISBN, String titolo, int annoDiPubblicazione, int numeroPagine, Prestito prestito, Periodicita periodicita) {
        super(ISBN, titolo, annoDiPubblicazione, numeroPagine, prestito);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "ISBN='" + getISBN() + '\'' +
                ", titolo='" + getTitolo() + '\'' +
                ", annoDiPubblicazione=" + getAnnoDiPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                ", periodicita=" + periodicita;
    }
}

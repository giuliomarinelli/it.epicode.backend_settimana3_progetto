package it.epicode.week3.progetto.entities;

import jakarta.persistence.*;

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
                ", periodicita=" + periodicita;
    }
}

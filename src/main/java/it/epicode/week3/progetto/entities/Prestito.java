package it.epicode.week3.progetto.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prestiti")
public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "utente_fk")
    private Utente utente;

    // un prestito concettualmente potrebbe comprendere anche piu di un libro, ma la traccia cita al singolare "elemento prestato"
    // Pertanto imposto la relazione come OneToOne e non OneToMany
    @OneToOne
    @JoinColumn(name = "elemento_prestato_fk")
    private FonteLeggibile elementoPrestato;

    @Column(name = "data_inizio_prestito")
    private LocalDate dataInizioPrestito;

    @Column(name = "data_restituzione_prevista")
    private LocalDate dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private LocalDate dataRestituzioneEffettiva;

    public Prestito() {}

    public Prestito(UUID id, Utente utente, FonteLeggibile elementoPrestato, LocalDate dataInizioPrestito, LocalDate dataRestituzioneEffettiva) {
        this.id = id;
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public FonteLeggibile getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(FonteLeggibile elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
        dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva;
    }
}

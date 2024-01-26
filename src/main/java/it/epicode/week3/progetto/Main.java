package it.epicode.week3.progetto;

import it.epicode.week3.progetto.Dao.PrestitoDao;
import it.epicode.week3.progetto.Dao.UtenteDao;
import it.epicode.week3.progetto.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Libro l = new Libro();
        l.setAutore("Umberto Eco");
        l.setTitolo("Il cimitero di Praga");
        l.setGenere("Dark");
        l.setNumeroPagine(500);
        l.setAnnoDiPubblicazione(1980);
        Libro l1 = new Libro();
        l1.setAutore("Umberto Eco");
        l1.setTitolo("Il pendolo di Foucault");
        l1.setGenere("Filosofico");
        l1.setNumeroPagine(500);
        l1.setAnnoDiPubblicazione(1980);
        Archivio.aggiungi(l);
        Archivio.aggiungi(l1);
        Rivista r = new Rivista();
        r.setTitolo("Moto2");
        r.setPeriodicita(Periodicita.MENSILE);
        r.setAnnoDiPubblicazione(2024);
        r.setNumeroPagine(40);
        Archivio.aggiungi(r);
        Archivio.aggiungi(l);

        Archivio.ricercaPerAnnoDiPubblicazione(2024).stream().forEach(System.out::println);

            Archivio.ricercaperAutore("Umberto Eco").stream().forEach(System.out::println);

        UtenteDao.open();
        PrestitoDao.open();



        Utente u = new Utente();
        u.setNome("Mario");
        u.setCognome("Rossi");
        u.setDataDiNascita(LocalDate.parse("1991-06-16"));

        Utente u1 = new Utente();
        u1.setNome("Alessandro");
        u1.setCognome("Bianchi");
        u1.setDataDiNascita(LocalDate.parse("1968-06-16"));

        UtenteDao.salva(u);
        UtenteDao.salva(u1);

        FonteLeggibile quattroRuote = Archivio.ricercaPerISBN("470bbee6-1c49-4f6c-8f25-2a95a44626fd");
        FonteLeggibile ilNomeDellaRosa = Archivio.ricercaPerISBN("fa2c004b-09fa-45c8-925f-8b50c7cd50df");

        Prestito p = new Prestito();
        p.setElementoPrestato(ilNomeDellaRosa);
        p.setDataInizioPrestito(LocalDate.now());
        p.setUtente(u);

        PrestitoDao.salva(p);

        Prestito p1 = new Prestito();
        p1.setUtente(u);
        p1.setElementoPrestato(quattroRuote);
        p1.setDataInizioPrestito(LocalDate.parse("2024-01-10"));
        p1.setDataRestituzioneEffettiva(LocalDate.now());
        PrestitoDao.salva(p1);





        System.out.println(Archivio.ricercaPerISBN("6da8100a-e1ca-4a26-a352-c8c18cb2974b"));

        Archivio.ricercaElementiNonRestituitiPerNumeroTessera(38).stream().forEach(System.out::println);

        Prestito prestito = new Prestito();
        prestito.setDataInizioPrestito(LocalDate.parse("2023-02-02"));
        PrestitoDao.salva(prestito);
        List<Prestito> prestitiScadutiNonRestituiti = Archivio.ricercaTuttiIPrestitiScadutiNonRestituiti();
        prestitiScadutiNonRestituiti.stream().forEach(System.out::println);
        UtenteDao.close();
        PrestitoDao.close();
        Archivio.close();


    }
}
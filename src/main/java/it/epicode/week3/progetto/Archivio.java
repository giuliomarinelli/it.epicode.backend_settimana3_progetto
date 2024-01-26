package it.epicode.week3.progetto;

import it.epicode.week3.progetto.Dao.UtenteDao;
import it.epicode.week3.progetto.entities.*;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Archivio {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void open() {
        emf = Persistence.createEntityManagerFactory("archivio_bibliografico_jpa");
        em = emf.createEntityManager();
    }


    public static void close() {
        em.close();
        emf.close();
    }

    public static void aggiungi(FonteLeggibile fl) {
        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(fl);
            et.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    Utilizzando il sistema di ereditarietà di tipo Table Per Class e affidando la generazione della
    chiave primaria alla superclasse, ogni id generato sarà diverso in ogni tabella. Si può osservare facilmente sia con
    le sequenze che con gli UUID che ogni volta che si istanzia un'entità del gruppo che è connesso tramite
    ereditarietà, la generazione passa ad uno "step" successivo. Pertanto nessuna delle tabelle collegate
    condividerà anche un singolo id uguale. Tutti gli id di una collezione di entity TABLE_PER_CLASS saranno
    univocamente diversi in tutte le entità. Tutti gli id di ogni tabella non corrispondono a nessun altro id
    di ciascuna tabella della collezione TABLE_PER_CLASS. Faccio questa premessa perché in questo caso
    posso sfruttare il polimorfismo e l'ereditarietà per fare un find unico verso un elemento di tipo superclasse.
     */


    public static void rimuoviPerISBN(UUID ISBN) {

        FonteLeggibile elementoCercato =  em.find(FonteLeggibile.class, ISBN);


        if (elementoCercato == null) {
            System.out.println("Si sta cercando di eliminare un elemento non presente in archivio");
            return;
        }
        try {

            EntityTransaction et = em.getTransaction();
            et.begin();
            if (elementoCercato instanceof Libro) {
                em.remove((Libro) elementoCercato);
            } else if (elementoCercato instanceof Rivista) {
                em.remove((Rivista) elementoCercato);
            }
            et.commit();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void rimuoviPerISBN(String ISBN) {
        rimuoviPerISBN(UUID.fromString(ISBN));
    }

    public static FonteLeggibile ricercaPerISBN(UUID ISBN) throws NullPointerException {
        FonteLeggibile elementoTrovato;
        try {

            return em.find(FonteLeggibile.class, ISBN);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static FonteLeggibile ricercaPerISBN(String ISBN) {
        return ricercaPerISBN(UUID.fromString(ISBN));
    }

    public static List<FonteLeggibile> ricercaPerAnnoDiPubblicazione(int anno) {
        try {
            Query q = em.createQuery("SELECT f FROM FonteLeggibile f WHERE f.annoDiPubblicazione = :anno");
            q.setParameter("anno", anno);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public static List<Libro> ricercaperAutore(String autore) {
        try {
            Query q = em.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore");
            q.setParameter("autore", autore);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public static List<FonteLeggibile> ricercaPerTitolo(String titolo) {
        try {
            String titoloDaCercare = ("%" + titolo + "%").toLowerCase(); // Ricerca case-insensitive
            Query q = em.createQuery("SELECT f FROM FonteLeggibile f WHERE LOWER(f.titolo) LIKE :titolo");
            q.setParameter("titolo", titoloDaCercare);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }
    public static List<FonteLeggibile> ricercaElementiNonRestituitiPerNumeroTessera(int n) {
        try {
            Query q = em.createQuery("SELECT f FROM FonteLeggibile f INNER JOIN Prestito" +
                    " p ON p.elementoPrestato = f  WHERE p.utente.numeroDiTessera = :n AND p.dataRestituzioneEffettiva IS NULL");
            q.setParameter("n", n);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public static ArrayList<Prestito> ricercaTuttiIPrestitiScadutiNonRestituiti() {
        try {
            Query q = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL");
            ArrayList<Prestito> lista = (ArrayList<Prestito>) q.getResultList();
            return lista.stream().filter(p -> LocalDate.now()
                    .isAfter(p.getDataRestituzionePrevista()))
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }





}




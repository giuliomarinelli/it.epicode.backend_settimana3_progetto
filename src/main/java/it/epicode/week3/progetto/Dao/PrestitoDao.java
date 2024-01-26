package it.epicode.week3.progetto.Dao;

import it.epicode.week3.progetto.entities.Prestito;
import it.epicode.week3.progetto.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.UUID;

public class PrestitoDao {
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

    public static void salva(Prestito p) {
        try {

            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(p);
            et.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Prestito trovaPerId(UUID id) throws NullPointerException {
        try {

            return em.find(Prestito.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Prestito trovaPerId(String id) throws NullPointerException {
        return trovaPerId(UUID.fromString(id));
    }
    public static void eliminaPerId(UUID id) {
        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            Prestito p = em.find(Prestito.class, id);
            em.remove(p);
            et.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void eliminaPerId(String id) {
        eliminaPerId(UUID.fromString(id));
    }

}

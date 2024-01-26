package it.epicode.week3.progetto.Dao;

import it.epicode.week3.progetto.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UtenteDao {
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

    public static void salva(Utente u) {
        try {

            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(u);
            et.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Utente trovaPerNumeroTessera(int n) throws NullPointerException {
        try {

            return em.find(Utente.class, n);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void eliminaPerNumeroTessera(int n) {
        try {

            EntityTransaction et = em.getTransaction();
            et.begin();
            Utente u = em.find(Utente.class, n);
            em.remove(u);
            et.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}

package org.example.dao.classes.db.ds;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory emf;
    static {
        emf = Persistence.createEntityManagerFactory("voteapp");
    }
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}

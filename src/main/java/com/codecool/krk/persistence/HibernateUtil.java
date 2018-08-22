package com.codecool.krk.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("cookbook");

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public static void closeEmFactory() {
        emFactory.close();
    }

}

package com.codecool.krk.persistence;

import com.codecool.krk.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnection {

    public static void main(String args[]) {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/cookbook_db",
                            "cook", "1234");

            System.out.println("Opened database successfully");

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("cookbook");
            EntityManager em = emf.createEntityManager();

            User user = new User("asia", new ArrayList<>());

            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

            em.close();
            emf.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

}

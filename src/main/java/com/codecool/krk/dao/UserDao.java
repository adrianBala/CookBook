package com.codecool.krk.dao;

import com.codecool.krk.model.Recipe;
import com.codecool.krk.model.User;
import com.codecool.krk.persistence.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDao {

    public User loadUser(long id) {
        EntityManager em = HibernateUtil.getEntityManager();

        User user = null;
        try {
            em.getTransaction().begin();
            user = em.find(User.class, id);
            Hibernate.initialize(user.getRecipes());
            for (Recipe recipe : user.getRecipes()) {
                Hibernate.initialize(recipe.getReviews());
                Hibernate.initialize(recipe.getIngredients());
            }

            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        return user;
    }

    public List<User> loadAllUsers() {
        EntityManager em = HibernateUtil.getEntityManager();

        List<User> users = null;
        try {
            em.getTransaction().begin();
            users = em.createQuery("from User ").getResultList();
            for (User user : users) {
                Hibernate.initialize(user.getRecipes());
                for (Recipe recipe : user.getRecipes()) {
                    Hibernate.initialize(recipe.getReviews());
                    Hibernate.initialize(recipe.getIngredients());
                }
            }

            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        return users;
    }

}

package com.codecool.krk.dao;

import com.codecool.krk.model.Recipe;
import com.codecool.krk.model.User;
import com.codecool.krk.persistence.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
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

    @Override
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

    @Override
    public boolean removeUser(long id) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            em.remove(user);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public boolean saveNewUser(User user) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public boolean updateUser(long id, String nickName) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            user.setNickName(nickName);
            em.merge(user);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
}

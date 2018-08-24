package com.codecool.krk.dao;

import com.codecool.krk.exception.NoSuchElementInDatabaseException;
import com.codecool.krk.model.Review;
import com.codecool.krk.persistence.HibernateUtil;
import org.hibernate.HibernateException;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

public class ReviewDaoImpl implements ReviewDao {

    @Override
    public boolean saveNewReview(Review review) {

        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(review);
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
    public boolean removeReview(long id) {

        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Review review = em.find(Review.class, id);
            if(review == null) {
                throw new NoSuchElementInDatabaseException();
            }
            em.remove(review);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } catch (NoSuchElementInDatabaseException e) {
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public boolean updateReview(int rating, String opinion, String author, Long id) {

        EntityManager em = HibernateUtil.getEntityManager();
        Review review = em.find(Review.class, id);

        if (review != null) {
            em.getTransaction().begin();
            review.setRating(rating);
            review.setOpinion(opinion);
            review.setAuthor(author);
            em.persist(review);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }

}

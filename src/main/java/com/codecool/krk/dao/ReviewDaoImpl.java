package com.codecool.krk.dao;

import com.codecool.krk.model.Review;
import com.codecool.krk.persistence.HibernateUtil;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;

public class ReviewDaoImpl implements ReviewDao {

    @Override
    public void saveNewReview(Review review) {

        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(review);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}

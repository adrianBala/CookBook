package com.codecool.krk.dao;

import com.codecool.krk.model.Review;
import com.codecool.krk.persistence.HibernateUtil;

import javax.persistence.EntityManager;

public class ReviewDaoImpl implements ReviewDao {

    @Override
    public void saveNewReview(Review review) {

        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(review);
        em.getTransaction().commit();
        em.close();

    }
}

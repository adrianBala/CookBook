package com.codecool.krk.dao;

import com.codecool.krk.model.Recipe;
import com.codecool.krk.persistence.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.util.List;

public class RecipeDao {

    public Recipe loadRecipe(long id) {
        EntityManager em = HibernateUtil.getEntityManager();

        Recipe recipe = null;
        try {
            em.getTransaction().begin();
            recipe = em.find(Recipe.class, id);
            Hibernate.initialize(recipe.getIngredients());
            Hibernate.initialize(recipe.getReviews());
            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        return recipe;
    }

    public List<Recipe> loadAllRecipes() {
        EntityManager em = HibernateUtil.getEntityManager();

        List<Recipe> recipes = null;
        try {
            em.getTransaction().begin();
            recipes = em.createQuery("from Recipe").getResultList();
            for (Recipe recipe : recipes) {
                Hibernate.initialize(recipe.getIngredients());
                Hibernate.initialize(recipe.getReviews());
            }
            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        return recipes;
    }

}

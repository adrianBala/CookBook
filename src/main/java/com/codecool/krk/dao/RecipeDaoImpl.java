package com.codecool.krk.dao;

import com.codecool.krk.model.Recipe;
import com.codecool.krk.persistence.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.util.List;

public class RecipeDaoImpl implements RecipeDao {

    @Override
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

    @Override
    public boolean removeRecipe(long recipeId) {
        EntityManager em = HibernateUtil.getEntityManager();
        Recipe recipe = em.find(Recipe.class, recipeId);
        if (recipe != null) {
            em.getTransaction().begin();
            em.remove(recipe);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;

    }

    @Override
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

    @Override
    public boolean updateRecipe(long id, String name, String instruction) {
        EntityManager em = HibernateUtil.getEntityManager();
        Recipe recipe = em.find(Recipe.class, id);

        if (recipe != null) {
            em.getTransaction().begin();
            recipe.setName(name);
            recipe.setInstruction(instruction);
            em.persist(recipe);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }

}

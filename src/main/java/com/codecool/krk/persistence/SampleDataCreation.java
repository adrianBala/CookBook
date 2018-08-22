package com.codecool.krk.persistence;

import com.codecool.krk.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class SampleDataCreation {

    public static void main(String args[]) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cookbook");
        EntityManager em = emf.createEntityManager();

        User user = new User("Asia", new ArrayList<>());
        User user1 = new User("Wiola", new ArrayList<>());
        User user2 = new User("Kasia", new ArrayList<>());
        User user3 = new User("Adrian", new ArrayList<>());
        User user4 = new User("Marysia", new ArrayList<>());

        Ingredient tomato = new Ingredient("tomato", 1, Unit.KILOGRAM );
        Ingredient potato = new Ingredient("potato", 0.5, Unit.KILOGRAM );
        Ingredient salt = new Ingredient("salt", 1, Unit.TABLESPOON );
        Ingredient pepper = new Ingredient("pepper", 1, Unit.TEASPOON );
        Ingredient milk = new Ingredient("milk", 1, Unit.LITRE );

        List<Ingredient> ingredients1 = new ArrayList<>();
        ingredients1.add(tomato);

        List<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(potato);

        List<Ingredient> ingredients3 = new ArrayList<>();
        ingredients3.add(salt);

        List<Ingredient> ingredients4 = new ArrayList<>();
        ingredients4.add(pepper);

        List<Ingredient> ingredients5 = new ArrayList<>();
        ingredients5.add(milk);

        Recipe recipe1 = new Recipe("tomato soup", user, ingredients1,
                                      "Add smth to smth", new ArrayList<Review>(),Category.SOUP);
        Recipe recipe2 = new Recipe("lasagne", user1, ingredients2,
                "Add smth to smth2", new ArrayList<Review>(),Category.MAIN_COURSE);

        Recipe recipe3 = new Recipe("ice cream", user2, ingredients3,
                "Add smth to smth3", new ArrayList<Review>(),Category.DESSERT);

        Recipe recipe4 = new Recipe("sandwiches", user3, ingredients4,
                "Add smth to smth4", new ArrayList<Review>(),Category.BREAKFAST);

        Recipe recipe5 = new Recipe("chicken soup", user4, ingredients5,
                "Add smth to smth5", new ArrayList<Review>(),Category.SOUP);

        Review review = new Review(3, "good", "Ola");
        Review review1 = new Review(4, "very good", "Jola");
        Review review2 = new Review(2, "nice", "Wiesio");
        Review review3 = new Review(3, "tasty", "Iza");
        Review review4 = new Review(5, "delicious", "Piotr");

        review.setRecipe(recipe1);
        review1.setRecipe(recipe2);
        review2.setRecipe(recipe3);
        review3.setRecipe(recipe4);
        review4.setRecipe(recipe5);

        recipe1.addReview(review);
        recipe2.addReview(review1);
        recipe3.addReview(review2);
        recipe4.addReview(review3);
        recipe5.addReview(review4);

        tomato.setRecipe(recipe1);
        potato.setRecipe(recipe2);
        salt.setRecipe(recipe3);
        pepper.setRecipe(recipe4);
        milk.setRecipe(recipe5);

        em.getTransaction().begin();
        em.persist(user);
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);

        em.persist(recipe1);
        em.persist(recipe2);
        em.persist(recipe3);
        em.persist(recipe4);
        em.persist(recipe5);

        em.persist(review);
        em.persist(review1);
        em.persist(review2);
        em.persist(review3);
        em.persist(review4);

        em.persist(tomato);
        em.persist(potato);
        em.persist(salt);
        em.persist(pepper);
        em.persist(milk);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}

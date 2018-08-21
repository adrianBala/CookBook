package com.codecool.krk.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private long id;
    private String name;
    private User author;
    private List<Ingredient> ingredients;
    private String instruction;
    private List<Review> reviews;
    private Category category;

    public Recipe() {

    }

    public Recipe(long id, String name, User author, String instruction, Category category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.ingredients = new ArrayList<Ingredient>();
        this.instruction = instruction;
        this.reviews = new ArrayList<Review>();
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getAuthor() {
        return author;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

package com.codecool.krk.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne
    @JsonBackReference
    private User author;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE,orphanRemoval = true)
    @JsonManagedReference
    private List<Ingredient> ingredients;

    private String instruction;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Review> reviews;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Recipe() {
    }

    public Recipe(String name, User author, List<Ingredient> ingredients, String instruction, List<Review> reviews,  Category category) {
        this.name = name;
        this.author = author;
        this.ingredients = ingredients;
        this.instruction = instruction;
        this.reviews = reviews;
        this.category = category;
    }

    public Recipe(String name, User author, List<Ingredient> ingredients, String instruction, List<Review> reviews,  Category category, long id) {
        this(name, author, ingredients, instruction, reviews, category);
        this.id = id;
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

    public void addReview(Review review) {
        review.setRecipe(this);
        this.reviews.add(review);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", ingredients=" + ingredients +
                ", instruction='" + instruction + '\'' +
                ", reviews=" + reviews +
                ", category=" + category +
                '}';
    }
}

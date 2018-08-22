package com.codecool.krk.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private long id;
    private int rating;
    private String opinion;
    private String author;

    @ManyToOne
    @JsonBackReference
    private Recipe recipe;

    public Review() {

    }

    public Review(int rating, String opinion, String author) {
        this.rating = rating;
        this.opinion = opinion;
        this.author = author;
    }

    public Review(int rating, String opinion, String author, long id) {
        this(rating,opinion,author);
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", opinion='" + opinion + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

package com.codecool.krk.model;

public class Review {

    private long id;
    private int rating;
    private String opinion;
    private String author;

    public Review() {

    }

    public Review(long id, int rating, String opinion, String author) {
        this.id = id;
        this.rating = rating;
        this.opinion = opinion;
        this.author = author;
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

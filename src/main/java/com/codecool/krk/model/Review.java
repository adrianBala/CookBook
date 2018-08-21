package com.codecool.krk.model;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
   @Id @GeneratedValue
    @Column(name = "rating")
    private int rating;

    @Column(name = "opinion")
    private String opinion;

    @Column(name = "author")
    private String author;

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

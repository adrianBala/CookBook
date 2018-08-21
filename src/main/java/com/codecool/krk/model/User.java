package com.codecool.krk.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="nick_name")
    private String nickName;

    @OneToMany
    private List<Recipe> recipes;

    public User() {
    }

    public User(String nickName, List<Recipe> recipes) {
        this.nickName = nickName;
        this.recipes = recipes;
    }

    public User(long id, String nickName, List<Recipe> recipes) {
        this(nickName, recipes);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", recipes=" + recipes +
                '}';
    }
}

package com.codecool.krk.model;

public class User {

    private long id;
    private String nickName;
    private List<Recipe> recipes;

    public User() {
    }

    public User(long id, String nickName, List<Recipe> recipes) {
        this.id = id;
        this.nickName = nickName;
        this.recipes = recipes;
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

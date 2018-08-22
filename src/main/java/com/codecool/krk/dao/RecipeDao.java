package com.codecool.krk.dao;

import com.codecool.krk.model.Recipe;

import java.util.List;

public interface RecipeDao {
    Recipe loadRecipe(long id);

    List<Recipe> loadAllRecipes();
}

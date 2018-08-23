package com.codecool.krk.dao;

import com.codecool.krk.model.Recipe;

import java.util.List;

public interface RecipeDao {

    Recipe loadRecipe(long id);

    boolean removeRecipe(long recipeId);

    List<Recipe> loadAllRecipes();

    boolean updateRecipe(Recipe recipe);
}

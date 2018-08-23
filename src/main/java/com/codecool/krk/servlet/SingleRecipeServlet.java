package com.codecool.krk.servlet;

import com.codecool.krk.dao.RecipeDao;
import com.codecool.krk.dao.RecipeDaoImpl;
import com.codecool.krk.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipes/*")
public class SingleRecipeServlet extends HttpServlet {

    RecipeDao recipeDao = new RecipeDaoImpl();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPart = request.getPathInfo();
        Long id = null;
        try {
            id = Long.parseLong(idPart.substring(1));
            System.out.println(id);
        } catch(NumberFormatException e) {
            //TODO send info about wrong type of input
        }

        if (recipeDao.removeRecipe(id)) {
            // TODO SUCCESSFUL
        } else {
            // TODO send info about wrong id
        }
    }
}

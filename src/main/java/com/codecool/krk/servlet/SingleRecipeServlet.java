package com.codecool.krk.servlet;

import com.codecool.krk.dao.RecipeDao;
import com.codecool.krk.dao.RecipeDaoImpl;
import com.codecool.krk.model.Recipe;
import com.codecool.krk.util.UriParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet() //"/recipes/*")
public class SingleRecipeServlet extends HttpServlet {

    RecipeDao recipeDao = new RecipeDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        long id = UriParser.extractIdFromUri(req.getRequestURI());

        Recipe recipe = recipeDao.loadRecipe(id);
        if (recipe != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String recipeJson = objectMapper.writeValueAsString(recipe);
            resp.getWriter().print(recipeJson);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String idPart = request.getPathInfo();
        Long id = null;
        try {
            id = Long.parseLong(idPart.substring(1));
            System.out.println(id);
        } catch(NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        if (recipeDao.removeRecipe(id)) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

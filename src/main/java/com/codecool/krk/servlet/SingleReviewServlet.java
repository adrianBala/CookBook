package com.codecool.krk.servlet;

import com.codecool.krk.dao.*;
import com.codecool.krk.model.Recipe;
import com.codecool.krk.model.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipes/*")
public class SingleReviewServlet extends HttpServlet {

    ReviewDao reviewDao = new ReviewDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Review review = createReview(req);
        String path = req.getPathInfo();
        String id = path.substring(path.indexOf("/") + 1, path.indexOf("/reviews"));
        System.out.println(id);
        RecipeDao recipeDao = new RecipeDaoImpl();
        Recipe recipe = recipeDao.loadRecipe(Long.parseLong(id));
        if(recipe != null) {
            review.setRecipe(recipe);
            reviewDao.saveNewReview(review);
        }

    }

    private Review createReview(HttpServletRequest req) {
        int rating = Integer.parseInt(req.getParameter("rating"));
        String opinion = req.getParameter("opinion");
        String author = req.getParameter("author");
        return new Review(rating, opinion, author);
    }
}

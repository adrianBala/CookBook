package com.codecool.krk.servlet;

import com.codecool.krk.dao.RecipeDao;
import com.codecool.krk.dao.RecipeDaoImpl;
import com.codecool.krk.dao.ReviewDao;
import com.codecool.krk.dao.ReviewDaoImpl;
import com.codecool.krk.model.Recipe;
import com.codecool.krk.model.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/reviews"})
public class ReviewsServlet extends HttpServlet {

    ReviewDao reviewDao = new ReviewDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Review review = createReview(req);
        Recipe recipe = (Recipe) req.getAttribute("recipe");
        review.setRecipe(recipe);
        reviewDao.saveNewReview(review);
    }

    private Review createReview(HttpServletRequest req) {
        int rating = Integer.parseInt(req.getParameter("rating"));
        String opinion = req.getParameter("opinion");
        String author = req.getParameter("author");
        return new Review(rating, opinion, author);
    }

}

package com.codecool.krk.servlet;

import com.codecool.krk.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/reviews/*"})
public class SingleReviewServlet extends HttpServlet {

    ReviewDao reviewDao = new ReviewDaoImpl();

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getPathInfo().replaceFirst("/", "");
        boolean isRemoved = reviewDao.removeReview(Long.parseLong(id));
        System.out.println(id + isRemoved);
        if(isRemoved) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reviewId = req.getPathInfo();
        Long id = null;
        try {
            id = Long.parseLong(reviewId.substring(1));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String data = br.readLine();

        Map<String, String> parsedData = parseFromData(data);
        int rating = Integer.parseInt(parsedData.get("rating"));
        String opinion = parsedData.get("opinion");
        String author = parsedData.get("author");

        if (reviewDao.updateReview(rating, opinion, author, id)) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private Map<String,String> parseFromData(String data) {

        Map<String, String> parameters = new HashMap<>();
        String[] pairs = data.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String value = null;
            try {
                value = new URLDecoder().decode(keyValue[1], "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            parameters.put(keyValue[0], value);
        }
        return parameters;
    }

}




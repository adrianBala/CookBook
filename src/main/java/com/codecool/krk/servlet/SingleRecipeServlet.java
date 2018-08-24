package com.codecool.krk.servlet;

import com.codecool.krk.dao.RecipeDao;
import com.codecool.krk.dao.RecipeDaoImpl;
import com.codecool.krk.model.Recipe;
import com.codecool.krk.model.User;
import com.codecool.krk.util.UriParser;
import com.fasterxml.jackson.databind.ObjectMapper;

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

@WebServlet("/recipes/*")
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = UriParser.extractIdFromUri(req.getRequestURI());
        Recipe recipe = recipeDao.loadRecipe(id);
        if(recipe != null) {
            req.setAttribute("recipe", recipe);
            req.getRequestDispatcher("/reviews").forward(req, resp);
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
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        if (recipeDao.removeRecipe(id)) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idPart = request.getPathInfo();
        Long id = null;
        try {
            id = Long.parseLong(idPart.substring(1));
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String data = br.readLine();

        Map<String, String> parsedData = parseFromData(data);
        String name = parsedData.get("name");
        String instruction = parsedData.get("instruction");

        if (recipeDao.updateRecipe(id, name, instruction)) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private Map<String, String> parseFromData(String data) throws UnsupportedEncodingException {

        Map<String, String> nameAndInstruction = new HashMap<>();
        String[] pairs = data.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            nameAndInstruction.put(keyValue[0], value);
        }
        return nameAndInstruction;
    }
}

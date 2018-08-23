package com.codecool.krk.servlet;

import com.codecool.krk.dao.UserDao;
import com.codecool.krk.dao.UserDaoImpl;
import com.codecool.krk.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/*")
public class SingleUserServlet extends HttpServlet {

    UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String uri = req.getRequestURI();
        String [] splitUri = uri.split("/");
        long id = Long.parseLong(splitUri[2]);

        User user = userDao.loadUser(id);

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        resp.getWriter().print(userJson);
    }
}
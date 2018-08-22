package com.codecool.krk.servlet;

import com.codecool.krk.dao.UserDao;
import com.codecool.krk.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        List<User> users = userDao.loadAllUsers();

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(users);

        resp.getWriter().print(userJson);
    }
}

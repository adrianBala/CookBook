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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<User> users = userDao.loadAllUsers();
        ObjectMapper objectMapper = new ObjectMapper();
        String usersJson = objectMapper.writeValueAsString(users);
        resp.getWriter().print(usersJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickName = req.getParameter("nickName");
        if (nickName == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        User user = new User(nickName, new ArrayList<>());

        boolean isSaved = userDao.saveNewUser(user);
        if (!isSaved) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

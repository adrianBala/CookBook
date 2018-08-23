package com.codecool.krk.servlet;

import com.codecool.krk.dao.UserDao;
import com.codecool.krk.dao.UserDaoImpl;
import com.codecool.krk.model.User;
import com.codecool.krk.util.UriParser;
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

        long id = UriParser.extractIdFromUri(req.getRequestURI());

        User user = userDao.loadUser(id);
        if (user != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String userJson = objectMapper.writeValueAsString(user);
            resp.getWriter().print(userJson);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = UriParser.extractIdFromUri(req.getRequestURI());

        boolean isDeleted = userDao.removeUser(id);
        if (!isDeleted) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickName = req.getParameter("nickName");

        long id = UriParser.extractIdFromUri(req.getRequestURI());

        boolean isUpdated = userDao.updateUser(id, nickName);
        if (!isUpdated) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
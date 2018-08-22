package com.codecool.krk.dao;

import com.codecool.krk.model.User;

import java.util.List;

public interface UserDao {
    User loadUser(long id);

    List<User> loadAllUsers();
}

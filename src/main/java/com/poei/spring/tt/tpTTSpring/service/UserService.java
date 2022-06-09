package com.poei.spring.tt.tpTTSpring.service;

import com.poei.spring.tt.tpTTSpring.model.User;

import java.util.List;

public interface UserService {


    List<User> getAll();

    User getById(Integer id);

    User getByUsername(String username);

    User createUser(User user);

    User getUserByUsernameAndPassword(String username, String password);

    void deleteUser(Integer id);

    User update(User user);
}
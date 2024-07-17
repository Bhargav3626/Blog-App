package com.sunbase.dao;

import com.sunbase.models.User;

public interface UserDAO {
    boolean registerUser(User user);
    User loginUser(String username, String password);
    User getUserById(int id);
    User getUserByUsername(String username);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    User getUserByEmail(String emial);
}

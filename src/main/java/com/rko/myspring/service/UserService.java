package com.rko.myspring.service;

import com.rko.myspring.model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}

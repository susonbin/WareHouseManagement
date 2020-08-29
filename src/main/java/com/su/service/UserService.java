package com.su.service;

import com.su.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserByName(String username);

}

package com.su.service.impl;

import com.su.dao.UserDao;
import com.su.domain.User;
import com.su.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        System.out.println("业务层：查询所有");
        return userDao.findAll();
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }
}

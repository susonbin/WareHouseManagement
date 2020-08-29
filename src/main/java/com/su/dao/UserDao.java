package com.su.dao;

import com.su.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from user")
    public List<User> findAll();

    @Select("select * from user where name=#{username}")
    public User findUserByName(String username);
}

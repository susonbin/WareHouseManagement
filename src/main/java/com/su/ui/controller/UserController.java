package com.su.ui.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.su.domain.Clothing;
import com.su.domain.User;
import com.su.service.UserService;
import com.su.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private User curUser;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println("表现层：查询所有");
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "success";
    }

    @RequestMapping("/login")
    public @ResponseBody User login(@RequestBody String json){
        System.out.println(json);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject.get("username"));
        System.out.println(jsonObject.get("password"));
        String username = (String)jsonObject.get("username");
        String password = (String)jsonObject.get("password");
        User user=userService.findUserByName(username);
        System.out.println(user);
        if(user==null){
            System.out.println("fail1");
            return null;
        }
        if(!user.getPassword().equals(password)){
            System.out.println(user.getPassword());
            System.out.println(password);
            System.out.println("fail2");
            return null;
        }
        curUser=user;
        return user;
    }

    @RequestMapping("/getCurUser")
    public @ResponseBody User getCurUser(){
        return curUser;
    }


}

package com.su.ui.controller;

import com.su.domain.User;
import com.su.service.UserService;
import com.su.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println("表现层：查询所有");
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "success";
    }

}

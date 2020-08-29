package com.su.ui.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.su.domain.Clothing;
import com.su.domain.Order;
import com.su.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/findAllOrders")
    public @ResponseBody List<Order> findAllOrders(){
        System.out.println("nice");
        List<Order> orders=orderService.findAllOrders();
        return orders;
    }

    @RequestMapping("/findOrderById")
    public @ResponseBody Order findOrderById(@RequestBody String json){
        System.out.println(json);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println("cc"+jsonObject.get("id"));
        Integer id= Integer.valueOf((String) jsonObject.get("id"));
        return orderService.findOrderById(id);
    }

    @RequestMapping("/updateOrder")
    public void updateOrder(@RequestBody Order order){
        System.out.println(order);
        orderService.updateOrder(order);
    }

}

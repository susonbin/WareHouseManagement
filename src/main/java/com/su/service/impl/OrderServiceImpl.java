package com.su.service.impl;

import com.su.dao.OrderDao;
import com.su.domain.Clothing;
import com.su.domain.Order;
import com.su.service.ClothingService;
import com.su.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ClothingService clothingService;

    @Override
    public Order findOrderById(Integer id) {
        System.out.println("service"+id);
        return orderDao.findOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        System.out.println("service"+order);
        //items转Json
        order.itemsToJsonString();
        System.out.println(order);
        orderDao.updateOrder(order);
    }

    @Override
    public List<Order> findAllOrders() {
        System.out.println("service");
        List<Order> orders=orderDao.findAllOrders();
        System.out.println(orders);
        return orders;
    }

    @Override
    public void addOrder(Order order) {

    }

    @Override
    public void deleteOrder(Order order) {

    }
}

package com.su.service;

import com.su.domain.Order;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderService {

    /**
     * 获取所有的订单
     * @return
     */
    List<Order> findAllOrders();

    /**
     * 根据订单id获取订单
     * @Param id
     */
    Order findOrderById(Integer id);

    /**
     * 更新订单状态
     * @Param id
     */
    void updateOrder(Order order);

    /**
     * 添加订单
     * @param order
     */
    void addOrder(Order order);

    /**
     * 删除订单
     * @param order
     */
    void deleteOrder(Order order);
}

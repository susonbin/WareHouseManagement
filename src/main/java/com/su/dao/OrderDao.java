package com.su.dao;

import com.su.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface OrderDao {

    /**
     * 获取所有的订单
     * @return
     */
    @Select("select * from `order`")
    List<Order> findAllOrders();

    /**
     * 根据订单id获取订单
     * @Param id
     */
    @Select("select * from `order` where id=#{id}")
    Order findOrderById(Integer id);

    /**
     * 更新订单状态
     * @Param id
     */
    @Update("update `order` set name=#{name},itemsJson=#{itemsJson},state=#{state} where id=#{id}")
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

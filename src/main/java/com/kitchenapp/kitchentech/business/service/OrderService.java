package com.kitchenapp.kitchentech.business.service;

import com.kitchenapp.kitchentech.business.model.Order;

import java.util.List;

public interface OrderService {

    public abstract List<Order> getAllOrders();
    public abstract Order createOrder(Order order);
    public abstract Order getOrderById(Long id);
    public abstract Order updateOrder(Order order);
    public abstract void deleteOrder(Long id);
    public void validateOrder(Order order);
}

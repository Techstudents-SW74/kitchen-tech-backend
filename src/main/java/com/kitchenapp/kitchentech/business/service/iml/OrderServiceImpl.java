package com.kitchenapp.kitchentech.business.service.iml;

import com.kitchenapp.kitchentech.business.model.Order;
import com.kitchenapp.kitchentech.business.repository.OrderRepository;
import com.kitchenapp.kitchentech.business.service.OrderService;
import com.kitchenapp.kitchentech.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        validateOrder(order);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(Order order) {
        validateOrder(order);
        Order orderToUpdate = orderRepository.findById(order.getId()).orElse(null);
        if(orderToUpdate != null){
            orderToUpdate.setState(order.getState());
            orderToUpdate.setTotalPrice(order.getTotalPrice());
            orderToUpdate.setProducts(order.getProducts());

            return orderRepository.save(orderToUpdate);
        }
        else{
            return null;
        }
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void validateOrder(Order order) {
        if(order.getProducts() == null){
            throw new ValidationException("La orden no tiene productos.");
        }
        if(order.getState() == null || order.getState().isEmpty()){
            throw new ValidationException("La orden requiere asignar un estado");
        }
    }
}

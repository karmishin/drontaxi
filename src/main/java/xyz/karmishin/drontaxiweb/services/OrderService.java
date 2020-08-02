package xyz.karmishin.drontaxiweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.karmishin.drontaxiweb.entities.Order;
import xyz.karmishin.drontaxiweb.repositories.OrderRepository;

import java.util.List;

public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    
    public List<Order> all() {
        return orderRepository.findAll();
    }
}

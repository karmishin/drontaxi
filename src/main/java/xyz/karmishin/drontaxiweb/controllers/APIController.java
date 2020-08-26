package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.karmishin.drontaxiweb.entities.Order;
import xyz.karmishin.drontaxiweb.repositories.OrderRepository;

@RestController
public class APIController {
    private final OrderRepository orderRepository;

    public APIController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping(path = "/orders/{id}/status", produces = "application/json")
    public Order.Status orderStatus(@PathVariable Long id) {
        return orderRepository.findById(id).get().getCurrentStatus();
    }
}

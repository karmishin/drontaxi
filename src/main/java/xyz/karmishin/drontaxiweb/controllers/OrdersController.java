package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.karmishin.drontaxiweb.entities.Order;
import xyz.karmishin.drontaxiweb.entities.User;
import xyz.karmishin.drontaxiweb.forms.OrderForm;
import xyz.karmishin.drontaxiweb.repositories.OrderRepository;
import xyz.karmishin.drontaxiweb.repositories.UserRepository;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrdersController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String orders(Model model, Principal principal) {
        User currentUser = userRepository.findByPhoneNumber(principal.getName());
        model.addAttribute("orders", orderRepository.findByUser(currentUser));
        return "orders";
    }

    @PostMapping
    public String processOrder(OrderForm orderForm, Principal principal, Model model) {
        if (orderForm.getDepartureAddress().isBlank() || orderForm.getDestinationAddress().isBlank()) {
            model.addAttribute("error", "Все поля должны быть заполнены");
            return "redirect:/orders"; // TODO
        }

        User currentUser = userRepository.findByPhoneNumber(principal.getName());
        Order order = orderForm.toOrder(currentUser);
        orderRepository.save(order);
        return "redirect:/orders";
    }
}

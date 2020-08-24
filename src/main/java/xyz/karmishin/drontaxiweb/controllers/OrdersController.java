package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.karmishin.drontaxiweb.entities.Order;
import xyz.karmishin.drontaxiweb.entities.User;
import xyz.karmishin.drontaxiweb.forms.OrderForm;
import xyz.karmishin.drontaxiweb.repositories.OrderRepository;
import xyz.karmishin.drontaxiweb.repositories.UserRepository;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private User currentUser;

    public OrdersController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void addAttributes(Model model, Principal principal) {
        currentUser = userRepository.findByPhoneNumber(principal.getName());
        model.addAttribute("orders", orderRepository.findByUser(currentUser));
        model.addAttribute("orderForm", new OrderForm());
    }

    @GetMapping
    public String orders() {
        return "orders";
    }

    @PostMapping
    public String processOrder(@Valid OrderForm orderForm, Errors errors, Principal principal, Model model) {
        if (errors.hasErrors()) {
            return "orders";
        }
        
        Order order = orderForm.toOrder(currentUser);
        orderRepository.save(order);
        return "redirect:/orders";
    }
}

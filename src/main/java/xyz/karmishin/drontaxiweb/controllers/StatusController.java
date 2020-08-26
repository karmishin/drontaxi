package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.karmishin.drontaxiweb.entities.Order;
import xyz.karmishin.drontaxiweb.repositories.OrderRepository;

@RequestMapping("/orders/{id}")
@Controller
public class StatusController {
    private final OrderRepository orderRepository;

    public StatusController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @ModelAttribute
    public void addAttributes(Model model, @PathVariable Long id) {
        Order order = orderRepository.findById(id).get();
        model.addAttribute("order", order);

        int progressBarWidth;
        switch (order.getCurrentStatus()) {
            case WAITING:
                progressBarWidth = 33;
                break;
            case IN_PROGRESS:
                progressBarWidth = 66;
                break;
            case COMPLETE:
                progressBarWidth = 100;
                break;
            default:
                progressBarWidth = 0;
        }
        model.addAttribute("progressBarWidth", progressBarWidth);
    }

    @GetMapping
    public String status() {
        return "order-status";
    }
}
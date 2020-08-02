package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {
    @GetMapping("/orders")
    public String orders(Model model) {
        return "orders";
    }
}

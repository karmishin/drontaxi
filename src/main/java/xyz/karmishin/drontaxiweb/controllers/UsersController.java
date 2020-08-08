package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.karmishin.drontaxiweb.entities.User;
import xyz.karmishin.drontaxiweb.repositories.UserRepository;
import xyz.karmishin.drontaxiweb.services.UserService;

import java.util.List;

@Controller
public class UsersController {
    UserRepository userRepository;
    UserService userService;

    public UsersController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);
        return "users";
    }
}

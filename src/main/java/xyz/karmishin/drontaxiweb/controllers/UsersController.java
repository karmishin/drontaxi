package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.karmishin.drontaxiweb.entities.Role;
import xyz.karmishin.drontaxiweb.entities.User;
import xyz.karmishin.drontaxiweb.repositories.UserRepository;
import xyz.karmishin.drontaxiweb.services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String users(Model model) {
        List<User> userList = userService.all();
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User authenticatedUser = userRepository.findByUsername(username);
        model.addAttribute("user", authenticatedUser);
        return "edit";
    }

    @GetMapping("/users/add")
    public String addUserGet(Model model) {
        model.addAttribute("user", new User());
        return "adduser";
    }

    @PostMapping("/users/add")
    public String addUserPost(@Valid @ModelAttribute("user") User newUser, @RequestParam(value="adminCheckbox", required = false) boolean adminCheckboxChecked, Model model) {
        if (adminCheckboxChecked) {
            newUser.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN", "Администратор")));
        }

        if (userService.saveUser(newUser)) {
            return "redirect:/users";
        } else {
            return "newuser";
        }
    }
    
    @GetMapping("/users/{id}/edit")
    public String editUserGet(@PathVariable Long id, Model model) {
        User userToUpdate = userRepository.getOne(id);
        model.addAttribute("user", userToUpdate);
        return "edit";
    }
    
    @PostMapping("/users/{id}/edit")
    public String editUserPost(@Valid @ModelAttribute("user") User updatedUser, @PathVariable Long id, Model model) {
        User userToUpdate = userRepository.getOne(id);
        userToUpdate.setUsername(updatedUser.getUsername());
        userRepository.save(userToUpdate);
        return "redirect:/users";
    }
    
    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model) {
        userRepository.delete(userRepository.getOne(id));
        return "redirect:/users";
    }

}

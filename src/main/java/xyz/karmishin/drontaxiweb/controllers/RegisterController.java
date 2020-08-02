package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.karmishin.drontaxiweb.entities.User;
import xyz.karmishin.drontaxiweb.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorList", bindingResult.getAllErrors());
            return "register";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "register";
        }
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "register";
        }

        return "redirect:/";
    }
}

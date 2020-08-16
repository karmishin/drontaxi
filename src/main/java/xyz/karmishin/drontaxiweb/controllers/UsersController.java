package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.karmishin.drontaxiweb.entities.User;
import xyz.karmishin.drontaxiweb.forms.RegistrationForm;
import xyz.karmishin.drontaxiweb.repositories.RoleRepository;
import xyz.karmishin.drontaxiweb.repositories.UserRepository;

import java.util.List;

@RequestMapping("/users")
@Controller
public class UsersController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UsersController(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String users(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);
        return "users";
    }

    @PostMapping("add")
    public String addUser(RegistrationForm registrationForm, Model model, @RequestParam(value = "isAdmin", required = false) boolean isAdmin) {
        String phoneNumber = registrationForm.getPhoneNumber();

        if (userRepository.findByPhoneNumber(phoneNumber) != null) {
            model.addAttribute("error", "Пользователь с таким номером уже зарегистрирован.");
            return "redirect:/users";
        }

        User user = registrationForm.toUser(passwordEncoder);

        user.getRoles().add(roleRepository.findBySystemName("ROLE_USER"));
        if (isAdmin) {
            user.getRoles().add(roleRepository.findBySystemName("ROLE_ADMIN"));
        }

        userRepository.save(user);
        return "redirect:/users";
    }

}

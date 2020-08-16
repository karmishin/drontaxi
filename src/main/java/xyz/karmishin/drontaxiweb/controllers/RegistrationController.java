package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.karmishin.drontaxiweb.entities.User;
import xyz.karmishin.drontaxiweb.forms.RegistrationForm;
import xyz.karmishin.drontaxiweb.repositories.RoleRepository;
import xyz.karmishin.drontaxiweb.repositories.UserRepository;

import java.time.LocalDate;
import java.util.Collections;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "register";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form, Model model) {
        String phoneNumber = form.getPhoneNumber();
        String password = form.getPassword();
        String passwordConfirm = form.getPasswordConfirm();
        LocalDate birthdate = LocalDate.parse(form.getBirthdate());
        LocalDate today = LocalDate.now();

        if (userRepository.findByPhoneNumber(phoneNumber) != null) {
            model.addAttribute("error", "Пользователь с таким номером уже зарегистрирован.");
            return "register";
        }

        if (!password.equals(passwordConfirm)) {
            model.addAttribute("error", "Пароли не совпадают.");
            return "register";
        }

        if (birthdate.isAfter(today.minusYears(18))) {
            model.addAttribute("error", "Сервис предназначен только для лиц старше 18 лет.");
            return "register";
        }

        User user = form.toUser(passwordEncoder);
        user.getRoles().add(roleRepository.findBySystemName("ROLE_USER"));
        userRepository.save(user);

        model.addAttribute("message", "Регистрация прошла успешно.");
        return "login";
    }
}

package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.karmishin.drontaxiweb.entities.Role;
import xyz.karmishin.drontaxiweb.entities.User;
import xyz.karmishin.drontaxiweb.forms.RegistrationForm;
import xyz.karmishin.drontaxiweb.repositories.RoleRepository;
import xyz.karmishin.drontaxiweb.repositories.UserRepository;
import xyz.karmishin.drontaxiweb.services.UserService;

import java.time.LocalDate;
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
    public String addUser(RegistrationForm registrationForm, Model model, @RequestParam(value = "adminCheckbox", required = false) boolean adminCheckbox) {
        String phoneNumber = registrationForm.getPhoneNumber();

        if (userRepository.findByPhoneNumber(phoneNumber) != null) {
            model.addAttribute("error", "Пользователь с таким номером уже зарегистрирован.");
            return "redirect:/users";
        }

        User user = registrationForm.toUser(passwordEncoder);

        user.getRoles().add(roleRepository.findBySystemName("ROLE_USER"));
        if (adminCheckbox) {
            user.getRoles().add(roleRepository.findBySystemName("ROLE_ADMIN"));
        }

        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("{id}/edit")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userRepository.getOne(id);
        model.addAttribute("user", user);
        model.addAttribute("adminCheckbox", user.getRoles().contains(roleRepository.findBySystemName("ROLE_ADMIN")));

        return "edit-user";
    }

    @PostMapping("{id}/edit")
    public String processEditing(@PathVariable Long id, RegistrationForm form, @RequestParam(value = "adminCheckbox", required = false) boolean adminCheckbox) {
        User userToUpdate = userRepository.getOne(id);

        if (form.getPassword() != null) {
            userToUpdate.setPassword(passwordEncoder.encode(form.getPassword()));
        }

        if (adminCheckbox) {
            userToUpdate.getRoles().add(roleRepository.findBySystemName("ROLE_ADMIN"));
        } else {
            userToUpdate.getRoles().remove(roleRepository.findBySystemName("ROLE_ADMIN"));
        }

        userToUpdate.setPhoneNumber(form.getPhoneNumber());
        userToUpdate.setBirthdate(LocalDate.parse(form.getBirthdate()));
        userRepository.save(userToUpdate);

        return "redirect:/users";
    }

    @GetMapping("{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}

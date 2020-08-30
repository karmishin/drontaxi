package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.karmishin.drontaxiweb.entities.User;
import xyz.karmishin.drontaxiweb.forms.UserForm;
import xyz.karmishin.drontaxiweb.repositories.UserRepository;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("isAdminCheckboxVisible", false);
        model.addAttribute("adminCheckbox", false);
    }

    @GetMapping
    public String profile(Model model, Principal principal) {
        model.addAttribute("user", userRepository.findByPhoneNumber(principal.getName()));
        return "edit-user";
    }

    @PostMapping
    public String processEditing(UserForm form, Principal principal) {
        User userToUpdate = userRepository.findByPhoneNumber(principal.getName());
        userToUpdate.setBirthdate(LocalDate.parse(form.getBirthdate()));

        if (form.getPassword() != null) {
            userToUpdate.setPassword(passwordEncoder.encode(form.getPassword()));
        }

        if (!form.getPhoneNumber().equals(principal.getName())) {
            userToUpdate.setPhoneNumber(form.getPhoneNumber());
            userRepository.save(userToUpdate);
            return "redirect:/logout";
        }

        userRepository.save(userToUpdate);
        return "redirect:/";
    }
}

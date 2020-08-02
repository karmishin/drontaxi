package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.karmishin.drontaxiweb.entities.Role;
import xyz.karmishin.drontaxiweb.services.RoleService;

import java.util.List;

@Controller
public class RolesController {

    @Autowired
    RoleService roleService;

    @GetMapping("/roles")
    public String roles(Model model) {
        List<Role> roleList = roleService.all();
        model.addAttribute("roles", roleList);
        return "roles";
    }
}

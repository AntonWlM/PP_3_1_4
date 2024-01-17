package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.security.Principal;

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    private final UserService userService;


    private final RoleService roleService;
    @Autowired
    public AdminController(UserServiceImp userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/user")
    public String getAdmin(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByEmail(principal.getName()));
        return "admin/user";
    }
    @GetMapping
    public String getListUsers(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByEmail(principal.getName()));
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.getListRoles());
        return "admin/list";
    }

    @GetMapping(value = "/edit")
    public String editUser(@ModelAttribute(value = "id") Long id,
                           Model model) {
        model.addAttribute("roles", roleService.getListRoles());
        model.addAttribute("user", userService.findUser(id));
        return "admin/list";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        String encodedPassword = new BCryptPasswordEncoder(12).encode(user.getPassword());
        if (!user.getPassword().isBlank()) {
            user.setPassword(encodedPassword);}
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @PatchMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") User user, Long id) {
        userService.updateUser(user, id);
        return "redirect:/admin/";
    }

    @DeleteMapping(value = "/delete")
    public String deleteUser(@ModelAttribute("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}



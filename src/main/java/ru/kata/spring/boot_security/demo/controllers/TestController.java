package ru.kata.spring.boot_security.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    @GetMapping("/test")
    public String test(Model model, Principal principal) {
        model.addAttribute("person", userService.findByEmail(principal.getName()));
        model.addAttribute("users", userService.findAll());
        return "test";
    }
    @PatchMapping("/admin/edit/{id}")
    public String postUpdate(@ModelAttribute("user") User user,
                             @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
}

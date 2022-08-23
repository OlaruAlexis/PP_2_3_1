package com.example.urok_1.controller;

import com.example.urok_1.model.User;
import com.example.urok_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MyController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showAllUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());
        return "all-users";
    }

    @RequestMapping("/addNewUser")
    public String form(Model model) {

        model.addAttribute("user", new User());

        return "user-form";
    }

    @RequestMapping("/saveUser")
    public String saveNewUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "redirect:/";
    }

    @RequestMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userService.getUser(id));

        return "user-form";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}

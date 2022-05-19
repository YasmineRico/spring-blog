package com.codeup.springblog.controllers;

import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
    private UserRepository userDao;


    public UserController(UserRepository userDao) {
        this.userDao = userDao;

    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

}

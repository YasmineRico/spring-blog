package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiceRoll {

    @GetMapping("/dice-roll")
    public String rollDice() {
        return "dice-roll";
    }

    @PostMapping("/dice-roll")
    public String checkDice(@RequestParam int num, Model model) {
        int random = (int) (Math.random() * 6 + 1);
        model.addAttribute("num", num);
        model.addAttribute("random", random);
        return "dice-roll";
    }


}

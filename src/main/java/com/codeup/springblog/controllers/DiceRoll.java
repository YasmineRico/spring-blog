package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceRoll {
    @GetMapping("/dice-roll")
    public String viewRollDice(){
        return "view/dice-roll";
    }

    @GetMapping("/dice-roll/{n}")
    public String viewResults(@PathVariable int n, Model model){

        int diceRoll = (int) (Math.random() * 6) + 1;
        String message;

        if(diceRoll == n){
            message = "You guessed correctly!";
        }else{
            message = "Incorrect. Try again";
        }

        model.addAttribute("n", n);
        model.addAttribute("diceRoll", diceRoll);

        model.addAttribute("message", message);

        return "dice-roll-results";
    }
}




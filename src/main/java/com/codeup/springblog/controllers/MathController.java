package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

// This controller should listen for requests for several routes that correspond to basic arithmetic operations and produce the result of the arithmetic.

@Controller
public class MathController {
    @RequestMapping(path = "/add/{a}/and/{b}", method = RequestMethod.GET)
    @ResponseBody
    public double add(@PathVariable double a, @PathVariable double b) {
        return (a + b);
    }

    @RequestMapping(path = "/subtract/{a}/and/{b}", method = RequestMethod.GET)
    @ResponseBody
    public double subtract(@PathVariable double a, @PathVariable double b) {
        return (a - b);
    }

    @RequestMapping(path = "/multiply/{a}/and/{b}", method = RequestMethod.GET)
    @ResponseBody
    public double multiply(@PathVariable double a, @PathVariable double b) {
        return (a * b);
    }

    @RequestMapping(path = "/divide/{a}/and/{b}", method = RequestMethod.GET)
    @ResponseBody
    public double divide(@PathVariable double a, @PathVariable double b) {
        return (a / b);
    }
}


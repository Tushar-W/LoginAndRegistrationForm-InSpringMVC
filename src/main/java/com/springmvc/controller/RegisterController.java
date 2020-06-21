package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("")
@Controller
public class RegisterController {

    @RequestMapping(method = RequestMethod.GET)
    public String registrationProcess(ModelMap model){
        model.addAttribute("message", "Hello spring mvc");
        return "hello";
    }
}

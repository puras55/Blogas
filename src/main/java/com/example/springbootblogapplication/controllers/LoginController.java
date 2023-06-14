package com.example.springbootblogapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // perima kliento uzklausa
public class LoginController {

    @GetMapping("/login")
    public String getLogin() {

        return "login"; // grazina login
    }
}

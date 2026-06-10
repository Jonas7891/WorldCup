package com.worldcup.worldCup.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String home() {
        return "Bienvenido";
    }

    @GetMapping("/private")
    public String privateRoute() {
        return "Ruta protegida";
    }
}
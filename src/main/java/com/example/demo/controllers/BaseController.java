package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BaseController {

    @GetMapping("/world2")
    public String hello() {
        return "Hello !";
    }

    
}
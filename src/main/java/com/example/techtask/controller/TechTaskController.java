package com.example.techtask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TechTaskController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Gasser's submission for Udin's tech task! =)";
    }
}

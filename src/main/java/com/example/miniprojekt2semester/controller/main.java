package com.example.miniprojekt2semester.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class main {

    @GetMapping("/")
    public String mainpage(){

        return null;
    }

    @GetMapping("/createUser")
    public String createUser(){


        return null;
    }
}

package com.example.miniprojekt2semester.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class main {

    @GetMapping("/index")
    public String mainpage(){
        return "index";
    }

    @GetMapping("/createUser")
    public String createUser(){


        return "userCreation";
    }

    @PostMapping("/Get-Info")
    public String creatingUser(){



        return "redirect:/index";
    }
}

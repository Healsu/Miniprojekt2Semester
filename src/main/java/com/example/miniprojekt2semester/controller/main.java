package com.example.miniprojekt2semester.controller;

import com.example.miniprojekt2semester.services.SQLfunction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;


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
    public String creatingUser(WebRequest dataFromForm){
        SQLfunction sqlFunction = new SQLfunction();

        sqlFunction.connectDB();

        String userName = (dataFromForm.getParameter("userName"));
        String userMail = (dataFromForm.getParameter("mail"));
        String userPassword = (dataFromForm.getParameter("password"));


        if (sqlFunction.addUserToDB(userName,userMail,userPassword)){
            return "redirect:/index";
        }
        else{
            return "failedScenario";
        }

    }
}

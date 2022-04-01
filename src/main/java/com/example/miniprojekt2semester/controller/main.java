package com.example.miniprojekt2semester.controller;

import com.example.miniprojekt2semester.services.SQLfunction;
import com.example.miniprojekt2semester.services.ValidateMail;
import com.example.miniprojekt2semester.model.user;
import com.mysql.cj.Session;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.model.IAttribute;


import javax.servlet.http.HttpSession;


@Controller
public class main {

    SQLfunction sqLfunction = new SQLfunction();

    @GetMapping("/index")
    public String mainpage(){
        return "index";
    }

    @PostMapping("login")
    public String login(WebRequest dataFromForm, HttpSession session){
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        if (sqLfunction.checkIfUserExists(email, password)){
            session.setAttribute("user",sqLfunction.userForSession(email,password));
            return "redirect:/";
        } else {
            return "redirct:/";
        }
    }


    @GetMapping("/userCreation")
    public String createUser(){
        return "userCreation";
    }

    @PostMapping("/Get-Info")
    public String creatingUser(WebRequest dataFromForm){
        SQLfunction sqlFunction = new SQLfunction();
        ValidateMail mailValidate = new ValidateMail();

        sqlFunction.connectDB();

        String userName = (dataFromForm.getParameter("userName"));
        String userMail = (dataFromForm.getParameter("mail"));
        String userPassword = (dataFromForm.getParameter("password"));

        if (mailValidate.isEmailValid(userMail) && sqlFunction.addUserToDB(userName,userMail,userPassword)) {
            return "redirect:/index";
        } else{
            return "failedScenario";
        }

    }
}

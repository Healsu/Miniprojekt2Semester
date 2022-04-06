package com.example.miniprojekt2semester.controller;

import com.example.miniprojekt2semester.services.SQLfunction;
import com.example.miniprojekt2semester.services.ValidateMail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


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
            return "userWebsite";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/createWishList")
    public String createWishList(){
        return "createWishlist";
    }

    @GetMapping("/invalidate-session")
    public String invalidate(HttpSession session){
        session.invalidate();
        return "redirect:index";
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

    @PostMapping("/Send-wish")
    public String sendingWish(WebRequest dataFromForm){
    String productName = (dataFromForm.getParameter("productName"));
    String priceName = (dataFromForm.getParameter("priceName"));
    String link = (dataFromForm.getParameter("link"));



        return null;
    }
}

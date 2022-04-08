package com.example.miniprojekt2semester.controller;

import com.example.miniprojekt2semester.model.user;
import com.example.miniprojekt2semester.model.wish;
import com.example.miniprojekt2semester.model.wishList;
import com.example.miniprojekt2semester.services.SQLfunction;
import com.example.miniprojekt2semester.services.ValidateMail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class main {

    SQLfunction sqLfunction = new SQLfunction();

    @GetMapping("/index")
    public String mainpage(){
        return "index";
    }

    @PostMapping("login")
    public String login(WebRequest dataFromForm, HttpSession session, Model model){
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");

            if (sqLfunction.checkIfUserExists(email, password)) {
                session.setAttribute("user", sqLfunction.userForSession(email, password));
                user user = (user) session.getAttribute("user");
                int userID = user.getUserID();
                ArrayList<wishList> userWishlist = sqLfunction.arrayOfWishlist(userID);
                model.addAttribute("userWishlist", userWishlist);
                return "userWebsite";
            }

        return "redirect:/";
    }

    @GetMapping("/viewWishlist")
    public String viewWishlist(Model model, @RequestParam("wishlistID") int id, HttpSession session){
        user user = (user) session.getAttribute("user");
        int userID = user.getUserID();
        ArrayList<wish> ListOfWish = sqLfunction.wishFromWishlistAndUserID(id, userID);
        model.addAttribute("ListOfWish",ListOfWish);
        return "viewWishlist";
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
        ValidateMail mailValidate = new ValidateMail();

        String userName = (dataFromForm.getParameter("userName"));
        String userMail = (dataFromForm.getParameter("mail"));
        String userPassword = (dataFromForm.getParameter("password"));

        if (mailValidate.isEmailValid(userMail) && sqLfunction.addUserToDB(userName,userMail,userPassword)) {
            return "redirect:/index";
        } else{
            return "failedScenario";
        }

    }

    @PostMapping("/createWishlist")
    public String createWishlist(WebRequest dataFromForm, HttpSession session){

        String wishlistName = dataFromForm.getParameter("wishlistName");
        user user = (user) session.getAttribute("user");
        int userID = user.getUserID();

        sqLfunction.createWishList(userID,wishlistName);

        return "createWish";
    }


    @PostMapping("/Send-wish")
    public String sendingWish(WebRequest dataFromForm, HttpSession session){

    String productName = (dataFromForm.getParameter("productName"));
    String priceName = (dataFromForm.getParameter("productPrice"));
    String link = (dataFromForm.getParameter("link"));
    user user = (user) session.getAttribute("user");

    int userID = user.getUserID();
    int wishlistID = sqLfunction.returnWishlistID(userID);
    sqLfunction.addWishToList(productName,priceName,link,wishlistID);
    sqLfunction.closeConnection();

        return "createWish";
    }
}

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

    //Jimmy's kode
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

    //Timmie's Kode
    @GetMapping("/userWebsite")
    public String userWebsite(HttpSession session, Model model){
        user user = (user) session.getAttribute("user");
        int userID = user.getUserID();
        ArrayList<wishList> userWishlist = sqLfunction.arrayOfWishlist(userID);
        model.addAttribute("userWishlist", userWishlist);
        return "userWebsite";
    }

    //Jimmy's kode
    @GetMapping("/viewWishlist")
    public String viewWishlist(Model model, @RequestParam("wishlistID") int id, HttpSession session){
        user user = (user) session.getAttribute("user");
        int userID = user.getUserID();
        ArrayList<wish> ListOfWish = sqLfunction.wishFromWishlistAndUserID(id, userID);
        model.addAttribute("ListOfWish",ListOfWish);
        return "viewWishlist";
    }
    //Jimmy's kode
    @GetMapping("/createWishList")
    public String createWishList(){
        return "createWishlist";
    }
    //Jimmy's kode
    @GetMapping("/invalidate-session")
    public String invalidate(HttpSession session){
        session.invalidate();
        return "redirect:index";
    }

    //Timmie's Kode
    @GetMapping("/userCreation")
    public String createUser(){
        return "userCreation";
    }

    //Timmie's Kode
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
    //Jimmy's kode
    @PostMapping("/createWishlist")
    public String createWishlist(WebRequest dataFromForm, HttpSession session){

        String wishlistName = dataFromForm.getParameter("wishlistName");
        user user = (user) session.getAttribute("user");
        int userID = user.getUserID();

        sqLfunction.createWishList(userID,wishlistName);

        return "createWish";
    }

    //Timmie's Kode
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

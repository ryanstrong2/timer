package org.ryanstrong.controllers;

import org.ryanstrong.models.User;
import org.ryanstrong.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryanstrong
 */
@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private UserDao userDao;

    @OneToMany
    @org.ryanstrong.models.JoinColumn(name="User_id")
    private List<User> users = new ArrayList<>();

    @RequestMapping(value="")
    public String index(Model model){
        return "redirect:user";
    }

//    @RequestMapping("login")
//    public String login(){
//        return "login.html";
//    }
//
//    @RequestMapping("/login-error.html")
//    public String loginError(Model model){
//        model.addAttribute("loginError", true);
//        return "login.html";
//    }
}


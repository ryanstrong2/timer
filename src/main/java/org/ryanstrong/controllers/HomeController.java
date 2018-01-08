package org.ryanstrong.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ryanstrong
 */
@Controller
@RequestMapping("")
public class HomeController {

//    @Autowired
//    private UserDao userDao;
//
//    @OneToMany
//    @org.ryanstrong.models.JoinColumn(name="User_id")
//    private List<User> users = new ArrayList<>();

    @RequestMapping(value="")
    public String index(Model model){
        return "redirect:user";
    }
}


package org.ryanstrong.controllers;

import org.ryanstrong.models.Timer;
import org.ryanstrong.models.User;
import org.ryanstrong.models.data.TimerDao;
import org.ryanstrong.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryanstrong on 6/12/17.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private TimerDao timerDao;

    @Autowired// creates class and object
    private UserDao userDao;

    @OneToMany
    @org.ryanstrong.models.JoinColumn(name="User_id")
    private List<Timer> timers = new ArrayList<>();

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDao.findAll());
        return "user/index";
    }
    @RequestMapping (value="add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "New User");
        model.addAttribute(new User());
        return "user/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User newUser, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "New User");
            return "user/add";
        }
//    todo    userDao.add(newUser);
        userDao.save(newUser);
        return "redirect:view/" + newUser.getId();
//        return "user";
    }
    @RequestMapping(value="view/{userId}", method = RequestMethod.GET)
    public  String view(Model model, @PathVariable int userId){
        User user = userDao.findOne(userId);

        model.addAttribute("title", user.getName());
        model.addAttribute("times", user.getTimeToPlay());
        return "user/view";
    }
    @RequestMapping(value="view", method = RequestMethod.POST)
    public String view(Model model, @ModelAttribute @Valid Timer newTimer, @PathVariable int userId,
            Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "Change Time");
            return "user/view/{userId}";
        }

        User user = userDao.findOne(userId);
        return "user/view/" + user.getId();
    }

}

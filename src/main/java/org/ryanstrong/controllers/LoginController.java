package org.ryanstrong.controllers;

import org.ryanstrong.models.Timer;
import org.ryanstrong.models.User;
import org.ryanstrong.models.data.ReportDao;
import org.ryanstrong.models.data.TimerDao;
import org.ryanstrong.models.data.UserDTO;
import org.ryanstrong.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ryanstrong on 6/12/17.
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired// creates class and object
    private UserDao userDao;

    @Autowired
    private TimerDao timerDao;

    @Autowired
    private ReportDao reportDao;

    @OneToMany
    @org.ryanstrong.models.JoinColumn(name = "User_id")
    private List<Timer> timers = new ArrayList<>();

    private Date now;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "LOG LOG IN");
//        model.addAttribute("users", userDao.findAll());
        return "login/index";
    }

//    @RequestMapping(value = "registration", method = RequestMethod.GET)
//    public String showRegistrationForm(WebRequest request, Model model) {
//        UserDTO userDto = new UserDTO();
//        model.addAttribute("user", userDto);
//        return "login/registration";
//    }
    @RequestMapping (value="registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("title", "New User");
        model.addAttribute(new User());
        return "login/registration";
    }
    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String registration(Model model, @ModelAttribute @Valid User newUser, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "New User");
            return "login/registration";
        }
        userDao.save(newUser);
        return "redirect:view/" + newUser.getId();
    }

    public ModelAndView registerUserAccount(
            @ModelAttribute("User") @Valid UserDTO accountDto,
            BindingResult result, WebRequest request, Errors errors){
        return new ModelAndView("index", "Message", "UserDTO");
    }
    @RequestMapping("login.html")
    public String login(){
            return "login.html";
    }
    @RequestMapping("login-error.html")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "login.html";
    }


}

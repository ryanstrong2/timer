package org.ryanstrong.controllers;

import org.ryanstrong.models.data.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("login")
public class LoginController {
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Login");
        return "login/index";
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";
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

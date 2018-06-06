package org.ryanstrong.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginController {
    @RequestMapping(value = "")
    public String index(Model model){ return "login/index";}
}

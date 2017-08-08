package org.ryanstrong.controllers;

import org.ryanstrong.models.data.ReportDao;
import org.ryanstrong.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ryanstrong on 8/5/17.
 */
@Controller
@RequestMapping("report")
public class ReportController {
    @Autowired
    private ReportDao reportDao;

    @Autowired
    private UserDao userDao;

//    @RequestMapping(value = "report/{userId}", method = RequestMethod.GET)
    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model
//            , @PathVariable Integer userId
    ){
//        User user = userDao.findOne(userId);

//        model.addAttribute("title", user.getName());
        model.addAttribute("report", reportDao.findAll());
        return "report/index";
    }
}

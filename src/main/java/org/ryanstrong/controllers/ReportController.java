package org.ryanstrong.controllers;

import org.ryanstrong.models.Report;
import org.ryanstrong.models.User;
import org.ryanstrong.models.data.ReportDao;
import org.ryanstrong.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model
    ){
        model.addAttribute("title", "Reports");
        model.addAttribute("reports", reportDao.findAll());
        model.addAttribute("aTimes", reportDao.findAll());
        model.addAttribute("report", "Report Page");
        return "report/index";
    }
    @RequestMapping(value="new/{userId}", method = RequestMethod.GET)
    public String addReport(Model model, @PathVariable Integer userId)
    {
        User user = userDao.findOne(userId);
        model.addAttribute("title", "Make a report for:  "+ user.getName());
        model.addAttribute("timeToPlay", user.getTimeToPlay());
        model.addAttribute("userId", userId);
        model.addAttribute(new Report());
        return "report/new/"+ user.getId();
    }
    @RequestMapping(value="new", method = RequestMethod.POST)
    public String addReport(Model model, @ModelAttribute @Valid Report newReport, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "New Report");
            return "report/new/";
        }
        reportDao.save(newReport);
        return "report/{userId}";
    }
}

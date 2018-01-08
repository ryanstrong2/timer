package org.ryanstrong.controllers;

import org.ryanstrong.models.Report;
import org.ryanstrong.models.Timer;
import org.ryanstrong.models.User;
import org.ryanstrong.models.data.ReportDao;
import org.ryanstrong.models.data.TimerDao;
import org.ryanstrong.models.data.UserDao;
import org.ryanstrong.models.forms.AlterTimeForm;
import org.ryanstrong.models.forms.ChangeTimeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ryanstrong on 6/12/17.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired// creates class and object
    private UserDao userDao;

    @Autowired
    private TimerDao timerDao;

    @Autowired
    private ReportDao reportDao;

    @OneToMany
    @org.ryanstrong.models.JoinColumn(name="User_id")
    private List<Timer> timers = new ArrayList<>();

    private Date now;

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
        userDao.save(newUser);
        return "redirect:view/" + newUser.getId();
    }
    @RequestMapping(value = "alter/{userId}", method = RequestMethod.GET)
    public String addTimeToPlay(Model model, @PathVariable Integer userId
//                                , @ModelAttribute Report report
    ){
        User user = userDao.findOne(userId);
        AlterTimeForm form = new AlterTimeForm(
                user.getTimeToPlay() , timerDao.findAll(),
//                reportDao.findOne(userId)
                    user.getReports(),
                user
        );
        model.addAttribute(new Report());
        model.addAttribute("title", "Add time for:  "+ user.getName());
        model.addAttribute("timeToPlay", user.getTimeToPlay());
        model.addAttribute("timerId", timers);
        model.addAttribute("userId", userId);
//        model.addAttribute("record", reportDao.findOne(userId).getRecord());
        model.addAttribute("report", user.getReports());
//        model.addAttribute(new Report());
        model.addAttribute("form", form);
        model.addAttribute("users", userDao.findAll());
        return "user/alter";
    }
    @RequestMapping(value="alter", method=RequestMethod.POST)
    public String addTimeToPlay(Model model,  @ModelAttribute @Valid AlterTimeForm form, Errors errors
            ,@RequestParam @Valid int timeToPlay
                                ,@ModelAttribute @Valid Report newReport
//                                ,@RequestParam int reportId
    ){
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "user/alter/";
        }
            User theUser = userDao.findOne(form.getUserId());
            Report theReport = reportDao.findOne(form.getUserId());
            List<Report> growList = theUser.getReports();
            Integer theNumber=(form.getTimeToPlay());
            Integer theTimerId = form.getTimerId();
            Integer total = theNumber + theTimerId;
            theUser.setTimeToPlay(total);
            newReport.setTimeToPlay(total);
            newReport.setUser(theUser);
            newReport.setUserName(theUser.getName());
            newReport.setInstant(LocalDateTime.now().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.RFC_1123_DATE_TIME));
            reportDao.save(newReport);
            userDao.save(theUser);
            return "redirect:/user/view/"+ theUser.getId();
    }
    @RequestMapping(value="remove", method=RequestMethod.GET)
    public String displayRemoveUserForm(Model model){
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("title", "Remove Users");
        return  "user/remove";
    }
    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveUserForm(Model model, @RequestParam Integer [] userIds, Errors errors){
        if(errors.hasErrors()){
            return "user/remove";
        }
//        if(userIds == null) {
//            return "user/remove";
//        }
        for (int userId:userIds){
            userDao.delete(userId);
        }
        return "redirect:";
    }
//todo make a report for week
    @RequestMapping(value="edit/{userId}", method = RequestMethod.GET)
    public  String addTime(Model model, @PathVariable Integer userId){
        User user = userDao.findOne(userId);
        ChangeTimeForm form = new ChangeTimeForm(timerDao.findAll(), user);
        model.addAttribute("title", "Increase time for: " + user.getName());
        model.addAttribute("timeToPlay", user.getTimeToPlay());
        model.addAttribute("form", form);
        return "user/edit";
    }
    @RequestMapping(value="edit", method = RequestMethod.POST)
    public String addTime(Model model, @ModelAttribute @Valid ChangeTimeForm form,
                       @RequestParam int timerId, @RequestParam int userId, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "user/edit";
        }
        User theUser = userDao.findOne(form.getUserId());
        Timer theTimer = timerDao.findOne(form.getTimerId());
        userDao.save(theUser);
        return "redirect:/user/view/" + theUser.getId();
    }
    @RequestMapping(value="remove-time/{userId}", method = RequestMethod.GET)
    public  String removeTime(Model model, @PathVariable Integer userId) {
        User user = userDao.findOne(userId);
        AlterTimeForm form = new AlterTimeForm(
                user.getTimeToPlay() , timerDao.findAll(),
                user.getReports(),
                user
        );
        model.addAttribute("title", "Reduce "+user.getName()+"'s Time");
        model.addAttribute("timeToPlay", user.getTimeToPlay());
        model.addAttribute("timerId", timers);
        model.addAttribute("userId", userId);
        model.addAttribute("form", form);
        model.addAttribute("users", userDao.findAll());
        return "user/remove-time";
    }

    @RequestMapping(value="remove-time", method = RequestMethod.POST)
    public String removeTime(
            Model model, @ModelAttribute @Valid AlterTimeForm form,
            @RequestParam @Valid int timeToPlay,
                       @ModelAttribute @Valid Report newReport,
                       Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "user/remove-time";
        }
        User theUser = userDao.findOne(form.getUserId());
        Integer theNumber=(form.getTimeToPlay());
        Integer theTimerId = form.getTimerId();
        Integer total = theNumber - theTimerId;
        theUser.setTimeToPlay(total);
        newReport.setTimeToPlay(total);
        newReport.setUser(theUser);
        newReport.setUserName(theUser.getName());
        newReport.setInstant(LocalDateTime.now().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.
                RFC_1123_DATE_TIME));
        reportDao.save(newReport);
        userDao.save(theUser);
        return "redirect:/user/view/" + theUser.getId();
    }

    @RequestMapping(value="time/{userId}", method = RequestMethod.GET)
    public  String time(Model model, @PathVariable Integer userId
    ) {
        User user = userDao.findOne(userId);
        User timeToPlay = userDao.findOne(user.getTimeToPlay());
        model.addAttribute("title",  user.getName());
        model.addAttribute("timeToPlay", user.getTimeToPlay());
        return "user/time";
    }

    @RequestMapping(value="time", method=RequestMethod.POST)
    public String time(
            Model model, @ModelAttribute @Valid AlterTimeForm form,
            @RequestParam int [] timerIds,
            @RequestParam (required=false, name="userId") Integer userId,
//                        @PathVariable Integer userId,
            Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "user/time";
        }
        for (int timerId:timerIds){
            timerDao.delete(timerId);}
        User theUser = userDao.findOne(userId);
        Timer theTimer = timerDao.findOne(form.getTimerId());
        userDao.save(theUser);
        return "redirect:/user/view/" + form.getUserId();
    }

    @RequestMapping(value="view/{userId}", method = RequestMethod.GET)
    public  String view(Model model, @PathVariable Integer userId){
        User user = userDao.findOne(userId);
        model.addAttribute("title", user.getName());
        model.addAttribute("timeToPlay", user.getTimeToPlay());
        model.addAttribute("reports", user.getReports());
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("user", user.getId());
        return "user/view";
    }
    @RequestMapping(value="view", method = RequestMethod.POST)
    public String view(Model model, @ModelAttribute @Valid ChangeTimeForm form,
                       @RequestParam int timerId,
                       Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "user/view/{userId}";
        }

        User theUser = userDao.findOne(form.getUserId());
        Timer theTimer = timerDao.findOne(form.getTimerId());
        userDao.save(theUser);
        return "user/view/" + theUser.getId();
    }
}
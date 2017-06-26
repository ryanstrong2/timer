package org.ryanstrong.controllers;

import org.ryanstrong.models.Timer;
import org.ryanstrong.models.User;
import org.ryanstrong.models.data.TimerDao;
import org.ryanstrong.models.data.UserDao;
import org.ryanstrong.models.forms.ChangeTimeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value="remove", method=RequestMethod.GET)
    public String displayRemoveTimeForm(Model model){
        model.addAttribute("timers", timerDao.findAll());
        model.addAttribute("title", "Remove Time");
        return  "timer/remove";
    }
    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveTimerForm(@RequestParam int [] ids){
        for (int id:ids){
            timerDao.delete(id);
        }
        return "redirect:";
    }
    @RequestMapping(value="view/{userId}", method = RequestMethod.GET)
    public  String view(Model model, @PathVariable int userId){
        User user = userDao.findOne(userId);
        ChangeTimeForm form = new ChangeTimeForm(timerDao.findAll(), user);
        model.addAttribute("title", user.getName());
        model.addAttribute("timeToPlay", user.getTimeToPlay());
        model.addAttribute("form", form);
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
//        theUser.view(theTimer);
        userDao.save(theUser);
        return "user/view/" + theUser.getId();
    }

}

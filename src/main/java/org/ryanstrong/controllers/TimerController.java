package org.ryanstrong.controllers;

import org.ryanstrong.models.Timer;
import org.ryanstrong.models.data.TimerDao;
import org.ryanstrong.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
//import java.util.ArrayList;


/**
 * Created by ryanstrong
 */
@Controller
@RequestMapping("timer")
public class TimerController {

    @Autowired
    private TimerDao timerDao;

    @Autowired
    private UserDao userDao;


        //Request path: /timer
    @RequestMapping(value="")
    public String index(Model model){

//            ArrayList<String> times = new ArrayList<>();
//            times.add("one");
//       todo get findAll to work
        model.addAttribute("times", timerDao.findAll());
                        //("key", localVariable or "enter the string")
        model.addAttribute("title", "Strong Timer");
        return "timer/index";
    }
    @RequestMapping (value="add", method = RequestMethod.GET)
    public String displayAddTimeForm(Model model){
        model.addAttribute("title", "Add Time");
        model.addAttribute(new Timer());
        return "timer/add";
    }
    @RequestMapping (value="add", method = RequestMethod.POST)
    protected String processAddTimeForm(@ModelAttribute @Valid Timer newTimer,
                                        Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Time");
        }
//     todo get save to work
        timerDao.save(newTimer);
        return "redirect:";
    }
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveTimeForm(Model model){
//      todo
        model.addAttribute("timers", timerDao.findAll());
        model.addAttribute("title", "Reduce Time");
        return "timer/remove";

    }
    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveTimeForm(@RequestParam int[] timerIds){
        for(int timerId:timerIds){
//        todo delete doesn't work
        timerDao.delete(timerId);
        }
        return "redirect:";
    }


}


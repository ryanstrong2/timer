package org.ryanstrong.controllers;

import org.ryanstrong.models.Timer;
import org.ryanstrong.models.User;
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
import java.util.ArrayList;
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

    @OneToMany
    @org.ryanstrong.models.JoinColumn(name="User_id")
    private List<Timer> timers = new ArrayList<>();

//    @ManyToOne
//    private Timer timers;

//    @ManyToOne
//    private List<User> users;

//    @ManyToOne
//    private Timer timer;

//    @OneToMany
//    @org.ryanstrong.models.JoinColumn(name="User_id")
//    private List<User> users = new ArrayList<>();

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
    public String add(Model model, @ModelAttribute @Valid User newUser,
                      @RequestParam int timeToPlay,
                      Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "New User");
            return "user/add";
        }
        userDao.save(newUser);
        return "redirect:view/" + newUser.getId();
//        return "user";
    }
    @RequestMapping(value = "alter/{userId}", method = RequestMethod.GET)
    public String addTimeToPlay(Model model, @PathVariable Integer userId
//    ,  Integer number
//                                , Integer timerId
    ){
        User user = userDao.findOne(userId);
//        Timer timer = timerDao.findOne(timerId);
//        Timer timer = (Timer) timerDao.findAll(); cast to org.ryanstrong.models.Timer cannot be done
        AlterTimeForm form = new AlterTimeForm(
                user.getTimeToPlay() ,
                timerDao.findAll()
//                , timerDao.findOne()
                , user
        );
//        AlterTimeForm form =new AlterTimeForm(userTimeToPlay, addition);
//        model.addAttribute("timers", form.getNumber());
        model.addAttribute("title", user.getName());
        model.addAttribute("timeToPlay", user.getTimeToPlay());
        model.addAttribute("timerId", timers);
        model.addAttribute("userId", userId);
        model.addAttribute("form", form);
        return "user/alter";
    }
    @RequestMapping(value="alter", method=RequestMethod.POST)
    public String addTimeToPlay(Model model,  @ModelAttribute @Valid AlterTimeForm form, Errors errors
            ,@RequestParam int timeToPlay
    ){
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "user/alter/";
        }
            User theUser = userDao.findOne(form.getUserId());
            Integer theNumber=(form.getTimeToPlay());
            Integer theTimerId = form.getTimerId();
            Integer total = theNumber + theTimerId;
            Timer theTimer=timerDao.findOne(form.getTimerId());
//            user.setTimeToPlay(user.getTimeToPlay());
//            theUser.aTimeToPlay(form.getTimerNumber());
//            theUser.aTimeToPlay(theTimer);
//            timeToPlay = timeToPlay + theTimer.getNumber();
            theUser.setTimeToPlay(total);
            userDao.save(theUser);
            return "redirect:/user/view/"+ theUser.getId();
//            Integer test = form.getTimeToPlay()+form.getTimerId();
    }
    @RequestMapping(value="play", method = RequestMethod.GET)
    public String displayPlay(Model model){
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("title", "Play Game");
        return "user/play";
    }
    @RequestMapping(value="play", method=RequestMethod.POST)
    public String processPlay(@RequestParam Integer[] timeToPlay){
//        userDao.replace
        return null;
    }
    @RequestMapping(value="remove", method=RequestMethod.GET)
    public String displayRemoveUserForm(Model model){
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("title", "Remove Users");
        return  "user/remove";
    }
    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveUserForm(@RequestParam Integer [] userIds){
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
//        model.addAttribute("timeToPlay", user.getTimer());
//        model.addAttribute("timer", user.getName());
//        model.addAttribute("timers", user.getTimers());
        model.addAttribute("timeToPlay", user.getTimeToPlay());
        model.addAttribute("form", form);
//        model.addAttribute("users", userDao.findAll());
//        model.addAttribute("timers", timerDao.findAll());

        return "user/edit";
    }
    @RequestMapping(value="edit", method = RequestMethod.POST)
    public String addTime(Model model, @ModelAttribute @Valid ChangeTimeForm form,
                       @RequestParam int timerId,
 @RequestParam int userId,
            Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "user/edit";
        }
        User theUser = userDao.findOne(form.getUserId());
        Timer theTimer = timerDao.findOne(form.getTimerId());

//        ChangeTimeForm addForm = new ChangeTimeForm(timerDao.findAll(), theUser);
//        ChangeTimeForm addForm = new ChangeTimeForm(timerDao.findAll(), form);
//        theUser.edit(theTimer);
//        theUser.addTime(theTimer);
        userDao.save(theUser);
//        userDao.save(form);
        return "redirect:/user/view/" + theUser.getId();
//        return "user/edit";
    }
    @RequestMapping(value="remove-time/{userId}", method = RequestMethod.GET)
    public  String removeTime(Model model, @PathVariable Integer userId) {
        User user = userDao.findOne(userId);
//        DeleteTimeForm form = new DeleteTimeForm(timerDao.findAll(), user);
        AlterTimeForm form = new AlterTimeForm(
                user.getTimeToPlay() , timerDao.findAll(), user
        );
        model.addAttribute("title", user.getName());
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
            @RequestParam int timeToPlay,
                       Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "user/remove-time";
        }
        User theUser = userDao.findOne(form.getUserId());
        Timer theTimer = timerDao.findOne(form.getTimerId());
        Integer theNumber=(form.getTimeToPlay());
        Integer theTimerId = form.getTimerId();
        Integer total = theNumber - theTimerId;
        theUser.setTimeToPlay(total);
        userDao.save(theUser);
        return "redirect:/user/view/" + theUser.getId();
//                form.getUserId();
    }

    @RequestMapping(value="time/{userId}", method = RequestMethod.GET)
    public  String time(Model model, @PathVariable Integer userId
            , @RequestParam (required=false, name="userTimeToPlay")Integer userTimeToPlay
                        , Timer timers
    ) {
        User user = userDao.findOne(userId);
        User timeToPlay = userDao.findOne(userTimeToPlay);
//        Timer timer= timerDao.findAll(timerId);
        model.addAttribute("title",  user.getName());
        model.addAttribute("timeToPlay", user.getTimeToPlay());
//        model.addAttribute("timers", timer;
//        AlterTimeForm form = new AlterTimeForm(userDao.findOne(userId), timeToPlay);
//        AlterTimeForm form = new AlterTimeForm(userTimeToPlay, timers, user);
//        AlterTimeForm form = new AlterTimeForm(
//                user.getTimeToPlay(),
//                timers
//                , user
//        );

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
//        ChangeTimeForm form = new ChangeTimeForm(timerDao.findAll(), user);
        model.addAttribute("title", user.getName());
        model.addAttribute("timeToPlay", user.getTimeToPlay());
//        model.addAttribute("timers", timerDao.findAll());
//        model.addAttribute("timers", user.getTimers());
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("user", user.getId());
//        model.addAttribute("form", form);

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
//        theUser.setTimers(theTimer);
        userDao.save(theUser);
        return "user/view/" + theUser.getId();
    }

}

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public class NotFoundException extends RuntimeException {}

//    @DeleteMapping("/remove-time/{timerId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteTimer(@PathVariable int timerId){
//        try{
//            userDao.delete(timerId);
//        } catch (EmptyResultDataAccessException ex){
//            throw new NotFoundException();
//
//        }
//    }
//    @RequestMapping(value="remove-time/{userId}", method= RequestMethod.DELETE)
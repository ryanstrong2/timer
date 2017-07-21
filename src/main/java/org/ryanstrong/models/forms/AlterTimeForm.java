package org.ryanstrong.models.forms;

import org.ryanstrong.models.Timer;
import org.ryanstrong.models.User;

import javax.validation.constraints.NotNull;

/**
 * Created by ryanstrong on 7/12/17.
 */
public class AlterTimeForm {
    @NotNull
    private int userId;

    private Integer timeToPlay;

    private User user;

    private int timerId;

    private Iterable<Timer> timers;
//    private Integer total;

    public void timer (Integer timer)
    {
        timeToPlay = timeToPlay + timer;
    }
    public AlterTimeForm(Integer timeToPlay, Iterable<Timer> timers, User user){
        this.timeToPlay = timeToPlay;
        this.timers = timers;
        this.user =user;
    }

    public int getUserId() {
        return userId;
    }

    public int getTimerId() {
        return timerId;
    }

//    public void setTimerId(int timerId) {
//        this.timerId = timerId;
//    }

    public Iterable<Timer> getTimers() {
        return timers;
    }

    public void setTimers(Iterable<Timer> timers) {
        this.timers = timers;
    }

    public void setUserId(int userId) {
        this.userId = userId;

    }

    public Integer getTimeToPlay() {
        return timeToPlay;
    }

    public void setTimeToPlay(Integer timeToPlay) {
        this.timeToPlay = timeToPlay;
    }

    public AlterTimeForm( User user){
        this.user=user;
    }
    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}

}

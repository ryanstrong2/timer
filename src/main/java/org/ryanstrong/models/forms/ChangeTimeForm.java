package org.ryanstrong.models.forms;

import org.ryanstrong.models.Timer;
import org.ryanstrong.models.User;

import javax.validation.constraints.NotNull;

/**
 * Created by ryanstrong on 6/25/17.
 */
public class ChangeTimeForm {
    @NotNull
    private int userId;

//    @NotNull
    private int timerId;

    private Iterable<Timer> timers;

    private User user;

    public ChangeTimeForm(){}

    public ChangeTimeForm(Iterable<Timer> timers, User user){
        this.timers=timers;
        this.user= user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user){ this.user=user;}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTimerId() {
        return timerId;
    }

    public void setTimerId(int timerId) {
        this.timerId = timerId;
    }

    public Iterable<Timer> getTimers() {
        return timers;
    }

    public void setTimers(Iterable<Timer> timers) {
        this.timers = timers;
    }
}

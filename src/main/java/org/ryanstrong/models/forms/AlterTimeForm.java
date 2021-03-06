package org.ryanstrong.models.forms;

import org.ryanstrong.models.Report;
import org.ryanstrong.models.Timer;
import org.ryanstrong.models.User;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ryanstrong on 7/12/17.
 */
public class AlterTimeForm {
    @NotNull
    private int userId;

    private Integer timeToPlay;

    private User user;
    @NotNull
    private int timerId;

    private Timer timer;

    private Integer number;

    private List<Report> record;
    private Iterable<Timer> timers;

    public AlterTimeForm(){}

    public AlterTimeForm(
            Integer timeToPlay,
            Iterable <Timer> timers
            , List<Report> record
            , User user
    ){
        this.timeToPlay = timeToPlay;
        this.timers = timers;
        this.record = record;
        this.user =user;
    }

    public int getUserId() {
        return userId;
    }

    public int getTimerId() {
        return timerId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public List<Report> getRecord() {
        return record;
    }
    public void setRecord(List<Report> record) {
        this.record = record;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getTimeToPlay() {
        return timeToPlay;
    }

    public void setTimeToPlay(Integer timeToPlay) {
        this.timeToPlay = timeToPlay;
    }

    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}

}

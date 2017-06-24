package org.ryanstrong.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by ryanstrong on 6/12/17.
 */
public class User {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    private Integer timeToPlay;

    @ManyToOne
    private Timer timer;

    public User(String name, Integer timeToPlay){
        this.name = name;
        this.timeToPlay = timeToPlay;
    }
    public User(){}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Integer getTimeToPlay() {
        return timeToPlay;
    }
    public void setTimeToPlay(Integer timeToPlay) {
        this.timeToPlay = timeToPlay;
    }

    public int getId()
    {
        return id;
    }
    public Timer getTimer(){ return timer;}

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    //    public void addMinute(Timer newUser){ timers.add(minute)}
//    public User(List<Timer> timers, String name){
//        this.timers=timers;
//        this.name = name;
//    }
}

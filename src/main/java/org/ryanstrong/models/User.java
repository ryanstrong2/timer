package org.ryanstrong.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ryanstrong on 6/12/17.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String name;

    private Integer timeToPlay;

//    @ManyToOne
//    private Timer timer;

//    @OneToOne
//    @JoinColumn(name="Timer_Id")
//    private Timer timers;

    @ManyToMany
    private List<Timer> timers;

//    @OneToMany
//    private List<Timer> timers;

    public User() {
    }

    public User(String name, Integer timeToPlay) {
        this.name = name;
        this.timeToPlay = timeToPlay;
    }

    public void addTime (Timer unit) {
        timers.add(unit);
    }

    public void play(Integer minutes){

        timeToPlay = timeToPlay - minutes;
    }

    public void removeTime(Timer unit){
        timers.remove(unit);
    }

    public User(List<Timer> timers, String name) {
       this.timers = timers;
       this.name =name;
    }


    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public List<Timer> getTimers(){
        return timers;
    }
    public void setTimers(List<Timer> timers){
        this.timers = timers;
    }
    public Integer getTimeToPlay() {
        return timeToPlay;
    }
    public void setTimeToPlay(Integer timeToPlay) {
        this.timeToPlay = timeToPlay;
    }

//    public void setTimeToPlay(Timer timer) {
//    }

//    public Timer getTimer(){ return timers;}

//    public void setTimer(Timer timer) {
//        this.timer = timer;
//    }
    //    public void addMinute(Timer newUser){ timers.add(minute)}




}

package org.ryanstrong.models;


import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    private Integer timeToPlay = 0;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Report> reports = new ArrayList<>();

//    @ManyToOne
//    private Timer timer;

//    @OneToOne
//    @JoinColumn(name="Timer_Id")
//    private Timer timers;

    @ManyToMany
    private List<Timer> timers;

    public User() {
    }

    public User(String name, Integer timeToPlay
            , List<Report> reports
    ) {
        this.name = name;
        this.timeToPlay=timeToPlay;
        this.reports = reports;
//        this.timeToPlay = timeToPlay + timer.getNumber();
//        timer = new Timer();
    }

//    public void addTime (Timer unit) {
//        timers.add(unit);
//    }

    public void aTimeToPlay(Timer input){
        Integer timeToPlay = getTimeToPlay() + input.getNumber();

    }
//    public void removeTime(Timer unit){
//        timers.remove(unit);
//    }
//    public User(List<Timer> timers, String name) {
//       this.timers = timers;
//       this.name =name;
//    }


    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public List<Report> getReports (){return reports;
    }
    public void setReports(List<Report> reports) {this.reports = reports;
    }

    public Integer getTimeToPlay() {
        return timeToPlay;
    }
    public void setTimeToPlay(Integer timeToPlay) {
        this.timeToPlay = timeToPlay;
    }

//    public void setTimeToPlay(Timer timer) {
//    }

//    public Timer getTimer(){ return timer;}

//    public void setTimer(Timer timer) {
//        this.timer = timer;
//    }
    //    public void addMinute(Timer newUser){ timers.add(minute)}

}

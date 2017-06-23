package org.ryanstrong.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ryanstrong on 6/12/17.
 */
public class User {
    @Id
    @GeneratedValue
    private int userId;

    @NotNull
    private String name;

    public User(){}

//    @ManyToMany
    private List<Timer> timers;

    public User(String name){
        this.name = name;
    }



//    public void addMinute(Timer newUser){ timers.add(minute)}
    public User(List<Timer> timers, String name){
        this.timers=timers;
        this.name = name;
    }

    public int getUserId()
    {
        return userId;
    }
}

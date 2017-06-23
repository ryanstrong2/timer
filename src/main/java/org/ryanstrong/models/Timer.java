package org.ryanstrong.models;

import javax.persistence.*;
import java.util.List;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

/**
 * Created by ryanstrong on 6/11/17.
 */

@Entity
public class Timer {

    @Id
    @GeneratedValue
    private int timerId;
    private int number;
//    private static int nextId=1;

    @JoinColumn
    @OneToMany(mappedBy="timers")
    private List<User> users;

    public Timer(int number) {
        this();
        this.number = number;
    }
    public Timer(){
//        timerId=nextId;
//        nextId++;
    }
    public int getTimerId() {
        return timerId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

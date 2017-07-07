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
    private int id;
    private int number;
//    private static int nextId=1;

    @ManyToMany(mappedBy = "timers")
    private List<User> users;

//    @OneToMany
//    @JoinColumn(name="timer_id")
//    private List<User> users
//        = new ArrayList<>()
//        ;

    public Timer() {
    }
    public Timer(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
//    public List<User> getUsers(){return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
}

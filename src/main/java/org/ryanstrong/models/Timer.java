package org.ryanstrong.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

/**
 * Created by ryanstrong on 6/11/17.
 */

@Entity
public class Timer {

    @Id
    @GeneratedValue
    private Integer id;
    private int number;
//    private static int nextId=1;

//    @ManyToMany(mappedBy = "timers")
//    private List<User> users;

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

    public Integer getId() {
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

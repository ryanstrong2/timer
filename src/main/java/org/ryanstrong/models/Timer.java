package org.ryanstrong.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by ryanstrong on 6/11/17.
 */

@Entity
public class Timer {

    @Id
    @GeneratedValue
    private Integer id;
    private int number;

    @ManyToMany(mappedBy = "timers")
    private List<User> users;

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

}

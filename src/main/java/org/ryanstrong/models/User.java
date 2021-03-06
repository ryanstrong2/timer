package org.ryanstrong.models;

import javax.persistence.*;
import javax.persistence.JoinColumn;
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

    private String password;

    private Integer timeToPlay = 0;

    private String email;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Report> reports;

    @ManyToMany
    private List<Timer> timers;

    public User() {
    }

    public User(String name, String password, Integer timeToPlay,List<Report> reports, String email) {
        this.name = name;
        this.password = password;
        this.timeToPlay=timeToPlay;
        this.reports = reports;
        this.email = email;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password=password;}
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
    public String getEmail(){return email;}
    public void setEmail(String email) {this.email=email;}
}

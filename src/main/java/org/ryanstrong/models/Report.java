package org.ryanstrong.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by ryanstrong on 8/5/17.
 */
@Entity
public class Report {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User user;

//    @ManyToOne
    private Date record;

//    private Instant instant;
    private String instant;
//    private LocalDate instant;

    private Integer timeToPlay;
    private String userName;

    public Report(){}

    public Report(
            String instant,
            User user,
            Integer timeToPlay
           , String userName){

        this.instant=instant;
        this.user=user;
        this.timeToPlay=timeToPlay;
        this.userName=userName;
    }

    public String getInstant() {
        return instant;
    }

    public void setInstant(String instant) {
        this.instant = instant;
    }
    public Integer getId(){return id;}

    public void setId(Integer id){this.id=id;}

    public Integer getTimeToPlay() {
        return timeToPlay;
    }

    public void setTimeToPlay(Integer timeToPlay) {
        this.timeToPlay = timeToPlay;
    }

    public User getUser() {
        return user;
    }
    public String getUserName(){return userName;}
    public void setUserName(String userName){this.userName = userName;}
    public void setUser(User user) {
        this.user = user;
    }
}

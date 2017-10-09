package org.ryanstrong.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

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
//    private Date record;

    private Instant instant;

    private Integer timeToPlay;
    private String userName;

//    @ManyToOne
//    private User user;



    public Report(){}



    public Report(
            Instant instant,
//            Date record,
            User user,
            Integer timeToPlay
           , String userName){

        this.instant=instant;
        this.user=user;
//        this.record=record;
        this.timeToPlay=timeToPlay;
        this.userName=userName;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }
    public Integer getId(){return id;}

    public void setId(Integer id){this.id=id;}

//    public Date getRecord(){return record;}

    public Integer getTimeToPlay() {
        return timeToPlay;
    }

    public void setTimeToPlay(Integer timeToPlay) {
        this.timeToPlay = timeToPlay;
    }

//    public void setRecord(Date record){this.record=record;}
    public User getUser() {
        return user;
    }
    public String getUserName(){return userName;}
    public void setUserName(String userName){this.userName = userName;}
    public void setUser(User user) {
        this.user = user;
    }
}

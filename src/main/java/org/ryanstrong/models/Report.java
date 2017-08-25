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

    @ManyToOne
    private Date record;

    private Integer timeToPlay;

//    @ManyToOne
//    private User user;



    public Report(){}

    public Report(
            Date record,
            User user,
            Integer timeToPlay){
        this.user=user;
        this.record=record;
        this.timeToPlay=timeToPlay;
    }

    public Integer getId(){return id;}

    public void setId(Integer Id){this.id=id;}

    public Date getRecord(){return record;}

    public Integer getTimeToPlay() {
        return timeToPlay;
    }

    public void setTimeToPlay(Integer timeToPlay) {
        this.timeToPlay = timeToPlay;
    }

    public void setRecord(Date record){this.record=record;}
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

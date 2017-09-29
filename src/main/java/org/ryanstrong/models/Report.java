package org.ryanstrong.models;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime record;

    private Integer timeToPlay;

    public Report(){}

    public Report(
            User user,
            LocalDateTime record,
            Integer timeToPlay){
        this.user=user;
        this.record=record;
        this.timeToPlay=timeToPlay;
    }

    public Integer getId(){return id;}

    public void setId(Integer Id){this.id=id;}

    public LocalDateTime getRecord(){return record;}

    public Integer getTimeToPlay() {
        return timeToPlay;
    }

    public void setTimeToPlay(Integer timeToPlay) {
        this.timeToPlay = timeToPlay;
    }

    public void setRecord(LocalDateTime record){this.record=record;}
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

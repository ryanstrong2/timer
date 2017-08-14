package org.ryanstrong.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

/**
 * Created by ryanstrong on 8/5/17.
 */
@Entity
public class Report {

    @Id
    @GeneratedValue
    private Integer id;



//    @ManyToOne
//    private User user;

    private Time record;

    public Report(){}

    public Report(Time record){
        this.record=record;
    }

    public Integer getId(){return id;}

    public void setId(Integer Id){this.id=id;}

    public Time getRecord(){return record;}

//    public void setRecord(Time record){this.record=record;}
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}

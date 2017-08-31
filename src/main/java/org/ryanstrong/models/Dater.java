package org.ryanstrong.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by ryanstrong on 8/24/17.
 */
@Entity
public class Dater {
    @Id
    @GeneratedValue
    private Integer id;
//    @OneToOne(mappedBy = "dates")
//    private Report report;
//    private User user;

//    public static void main(String[] args){
//        java.util.Date record = new java.util.Date();
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yy, hh:mm:ss a");
//    }
    private void dateTime(){
        LocalDateTime currentTime= LocalDateTime.now();
    }
//
//    public DateTime(String[] args){
//        LocalDate record = new LocalDate();
//
//    }
    public Dater(){ }
//    public Dater (
//            Report report
//    ){
//        this.report = report;
//
//    }
//
//    public Report getReport() {
//        return report;
//    }

//    public void setReport(Report report) {
//        this.report = report;
//    }


//    public java.util.Date ;
//                (int year,
//    int month,
//    int date,
//    int hrs,
//    int min) {
//        this.year=year;
//        this.month=month;
//        this.date=date;
//        this.hrs=hrs;
//        this.min=min;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    }
//}

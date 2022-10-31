package com.example.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "thread_status")
public class RedisThreadStatus  {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    

    @Column(name = "last_run_date_time")
    private String lastRunDateTime;



    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastRunDateTime() {
        return this.lastRunDateTime;
    }

    public void setLastRunDateTime(String lastRunDateTime) {
        this.lastRunDateTime = lastRunDateTime;
    }

    


}
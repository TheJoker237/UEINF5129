/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bahanag;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Nick Bahanag
 */
public class ColumnCont1 {
private  int id;
private Date time; 
private String event;
private String resource;
private String user_name;
private String machine_name;
 

    public ColumnCont1(int Id, Date Time,String event, String resource , String user_name,String machine_name ) {
        this.id = Id;
        this.time = Time;
        this.event=event;
        this.machine_name = machine_name;
        this.user_name = user_name;
        this.resource=resource;
    }

    public ColumnCont1() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    
  public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }  
    
    
    
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
    
        
    
}

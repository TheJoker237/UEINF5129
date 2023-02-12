/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bahanag;

import java.util.Date;
import java.sql.Time;

/**
 *
 * @author Nick Bahanag
 */
public class Line {
    
private  int id;
private String time; 
private String event;
private String resource;
private String user_name;
private String machine_name;    

    public Line(int id, String time, String event, String resource, String user_name, String machine_name) {
        this.id = id;
        this.time = time;
        this.event = event;
        this.resource = resource;
        this.user_name = user_name;
        this.machine_name = machine_name;
    }

    public Line() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }
    
    
}

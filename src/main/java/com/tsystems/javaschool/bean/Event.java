package com.tsystems.javaschool.bean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("helloworld")
@ViewScoped
public class Event implements Serializable {
    private String time;
    private String date;
    private String patient;
    private String prescription;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Event() {
    }

    public Event(String time, String date, String patient, String prescription) {
        this.time = time;
        this.date = date;
        this.patient = patient;
        this.prescription = prescription;
    }
    public String getMessage() {
        return "Hello World!";
    }
}

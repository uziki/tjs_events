package com.tsystems.javaschool.bean;


public class Event {
    private String time;
    private String patient;
    private String eventPrescription;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getEventPrescription() {
        return eventPrescription;
    }

    public void setEventPrescription(String eventPrescription) {
        this.eventPrescription = eventPrescription;
    }

    public Event() {
    }

    public Event(String time, String patient, String eventPrescription) {
        this.time = time;
        this.patient = patient;
        this.eventPrescription = eventPrescription;
    }
}

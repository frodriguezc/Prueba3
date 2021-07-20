package com.example.prueba3;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {

    private int eventId, ownerId;
    private Date evDate, alertDate;
    private String evName,evObs, evLocation;
    private boolean evImportant;

    public Event(int eventId, int ownerId, Date evDate, Date alertDate, String evName, String evObs, String evLocation, boolean evImportant) {
        this.eventId = eventId;
        this.ownerId = ownerId;
        this.evDate = evDate;
        this.alertDate = alertDate;
        this.evName = evName;
        this.evObs = evObs;
        this.evLocation = evLocation;
        this.evImportant = evImportant;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Date getEvDate() {
        return evDate;
    }

    public void setEvDate(Date evDate) {
        this.evDate = evDate;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }

    public String getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName = evName;
    }

    public String getEvObs() {
        return evObs;
    }

    public void setEvObs(String evObs) {
        this.evObs = evObs;
    }

    public String getEvLocation() {
        return evLocation;
    }

    public void setEvLocation(String evLocation) {
        this.evLocation = evLocation;
    }

    public boolean isEvImportant() {
        return evImportant;
    }

    public void setEvImportant(boolean evImportant) {
        this.evImportant = evImportant;
    }
}

package fr.on.mange.quoi.organizer.facade.dto;

import java.util.ArrayList;
import java.util.List;

public class OrganizerDTO {

    private String label;
    private DayOrganizerDTO monday;
    private DayOrganizerDTO tuesday;
    private DayOrganizerDTO wednesday;
    private DayOrganizerDTO thursday;
    private DayOrganizerDTO friday;
    private DayOrganizerDTO saturday;
    private DayOrganizerDTO sunday;
    private DayOrganizerDTO noMatterDay;

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setMonday(DayOrganizerDTO monday) {
        this.monday = monday;
    }

    public DayOrganizerDTO getMonday() {
        return monday;
    }

    public DayOrganizerDTO getTuesday() {
        return tuesday;
    }

    public void setTuesday(DayOrganizerDTO tuesday) {
        this.tuesday = tuesday;
    }

    public DayOrganizerDTO getWednesday() {
        return wednesday;
    }

    public void setWednesday(DayOrganizerDTO wednesday) {
        this.wednesday = wednesday;
    }

    public DayOrganizerDTO getThursday() {
        return thursday;
    }

    public void setThursday(DayOrganizerDTO thursday) {
        this.thursday = thursday;
    }

    public DayOrganizerDTO getFriday() {
        return friday;
    }

    public void setFriday(DayOrganizerDTO friday) {
        this.friday = friday;
    }

    public DayOrganizerDTO getSaturday() {
        return saturday;
    }

    public void setSaturday(DayOrganizerDTO saturday) {
        this.saturday = saturday;
    }

    public DayOrganizerDTO getSunday() {
        return sunday;
    }

    public void setSunday(DayOrganizerDTO sunday) {
        this.sunday = sunday;
    }

    public DayOrganizerDTO getNoMatterDay() {
        return noMatterDay;
    }

    public void setNoMatterDay(DayOrganizerDTO noMatterDay) {
        this.noMatterDay = noMatterDay;
    }
}

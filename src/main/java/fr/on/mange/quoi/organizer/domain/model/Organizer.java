package fr.on.mange.quoi.organizer.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Organizer {

    private Optional<String> optId = Optional.empty();

    private String label;

    private List<DayOrganizer> days = new ArrayList<>();

    private Optional<String> optUserId = Optional.empty();


    public Optional<String> getOptId() {
        return optId;
    }

    public void setOptId(Optional<String> optId) {
        this.optId = optId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void insert(DayOrganizer meal) {
        days.add(meal);
    }

    public List<DayOrganizer> days() {
        return new ArrayList<>(days);
    }

    public List<DayOrganizer> getDays() {
        return days;
    }

    public void setDays(List<DayOrganizer> days) {
        this.days = days;
    }

    public Optional<String> getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(Optional<String> optUserId) {
        this.optUserId = optUserId;
    }

}

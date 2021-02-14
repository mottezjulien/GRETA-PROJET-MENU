package fr.on.mange.quoi.organizer.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Organizer {

    private Optional<String> optId =  Optional.empty();

    private String label;

    private List<DayOrganizer> meals = new ArrayList<>();

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
        meals.add(meal);
    }

    public List<DayOrganizer> meals() {
        return new ArrayList<>(meals);
    }






}

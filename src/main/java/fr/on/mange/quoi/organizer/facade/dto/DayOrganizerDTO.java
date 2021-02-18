package fr.on.mange.quoi.organizer.facade.dto;

import java.util.ArrayList;
import java.util.List;

public class DayOrganizerDTO {

    private String id;

    private List<MealOrganizerDTO> meals = new ArrayList<>();

    public List<MealOrganizerDTO> getMeals() {
        return meals;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

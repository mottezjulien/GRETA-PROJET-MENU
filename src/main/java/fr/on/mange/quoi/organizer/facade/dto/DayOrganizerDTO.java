package fr.on.mange.quoi.organizer.facade.dto;

import java.util.ArrayList;
import java.util.List;

public class DayOrganizerDTO {

    private List<MealOrganizerDTO> meals = new ArrayList<>();

    public List<MealOrganizerDTO> getMeals() {
        return meals;
    }

}

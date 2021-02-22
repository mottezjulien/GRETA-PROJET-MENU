package fr.on.mange.quoi.organizer.domain.model;

import fr.on.mange.quoi.organizer.domain.model.choice.ChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoriesChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.day.Day;
import fr.on.mange.quoi.organizer.domain.model.day.DayNoMatter;
import fr.on.mange.quoi.organizer.domain.model.day.DayOfWeek;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DayOrganizer {

    private Optional<String> optId;

    private Day day;

    private Map<MealOrganizer, ChoiceOrganizer> choiceByType = new HashMap<>();

    private Organizer organizer;

    private java.time.DayOfWeek dayType;

    private String dayValue;

    public String getDayValue() {
        return dayValue;
    }

    public void setDayValue(String dayValue) {
        this.dayValue = dayValue;
    }

    public DayOrganizer(Optional<String> optId, Day day, Map<MealOrganizer, ChoiceOrganizer> choiceByType) {
        this.optId = optId;
        this.day = day;
        this.choiceByType = choiceByType;

    }

    public DayOrganizer(Organizer organizer, java.time.DayOfWeek dayType) {
        this.organizer = organizer;
        this.dayType = dayType;
    }


    public boolean isSameDay(java.time.DayOfWeek dayOfWeek) {
        return day instanceof DayOfWeek
                && ((DayOfWeek) day).toJavaTime().equals(dayOfWeek);
    }

    public boolean isNoMatterDay() {
        return day instanceof DayNoMatter;
    }

    public Map<MealOrganizer, ChoiceOrganizer> choiceByType() {
        return new HashMap<>(choiceByType);
    }

    public Optional<String> getOptId() {
        return optId;
    }

    public void put(MealOrganizer meal, ChoiceOrganizer choice) {
        choiceByType.put(meal, choice);
    }




}

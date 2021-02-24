package fr.on.mange.quoi.organizer.domain.model;

import fr.on.mange.quoi.organizer.domain.model.choice.ChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.day.Day;
import fr.on.mange.quoi.organizer.domain.model.day.DayNoMatter;
import fr.on.mange.quoi.organizer.domain.model.day.DayOfWeekModel;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DayOrganizer {

    private Optional<String> optId = Optional.empty();

    private Day day;

    private Map<MealOrganizer, ChoiceOrganizer> choiceByType = new HashMap<>();

    private Organizer organizer;

    private java.time.DayOfWeek dayType;


    public DayOrganizer(Optional<String> optId, Day day, Map<MealOrganizer, ChoiceOrganizer> choiceByType) {
        this.optId = optId;
        this.day = day;
        this.choiceByType = choiceByType;
    }

    public DayOrganizer(Day day) {
        this.day = day;
    }

    public DayOrganizer(DayOfWeek dayOfWeek) {
        this.day = new DayOfWeekModel(dayOfWeek);
    }

    public boolean isSameDay(java.time.DayOfWeek dayOfWeek) {
        return day instanceof DayOfWeekModel
                && ((DayOfWeekModel) day).toJavaTime().equals(dayOfWeek);
    }

    public boolean isNoMatterDay() {
        return day instanceof DayNoMatter;
    }

    public String strDay() {
        if (isNoMatterDay()) {
            return "No Matter";
        }
        return ((DayOfWeekModel) day).toJavaTime().toString();
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

    public Optional<DayOfWeekModel> toDayOfWeek () {
        if (isNoMatterDay()) {
            return Optional.empty();
        }
        return Optional.of((DayOfWeekModel) day);
    }


}

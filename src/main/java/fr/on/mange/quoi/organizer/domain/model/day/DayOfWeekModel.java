package fr.on.mange.quoi.organizer.domain.model.day;

import java.time.DayOfWeek;

public class DayOfWeekModel implements Day {

    private final DayOfWeek dayOfWeek;

    public DayOfWeekModel(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek toJavaTime() {
        return dayOfWeek;
    }

}

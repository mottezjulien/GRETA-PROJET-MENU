package fr.on.mange.quoi.organizer.domain.model.day;

public class DayOfWeek implements Day {

    private final java.time.DayOfWeek dayOfWeek;

    public DayOfWeek(java.time.DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek(String dayOfWeek) {
        this.dayOfWeek = java.time.DayOfWeek.valueOf(dayOfWeek);
    }

    public java.time.DayOfWeek toJavaTime() {
        return dayOfWeek;
    }

}

package fr.on.mange.quoi.organizer.facade;


import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;

public class AddChoiceDTORequest {

    private String dayId;

    private MealOrganizer listChoice;

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public MealOrganizer getListChoice() {
        return listChoice;
    }

    public void setListChoice(MealOrganizer listChoice) {
        this.listChoice = listChoice;
    }
}

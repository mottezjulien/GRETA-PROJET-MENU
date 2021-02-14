package fr.on.mange.quoi.organizer.domain.model.choice;

public class RecipeDishChoiceOrganizer implements ChoiceOrganizer {

    private String recipeDishId;

    private String label;

    public RecipeDishChoiceOrganizer(String recipeDishId, String label) {
        this.recipeDishId = recipeDishId;
        this.label = label;
    }

    public String recipeDishId() {
        return recipeDishId;
    }

    public String label() {
        return label;
    }
}

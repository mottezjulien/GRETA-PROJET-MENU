package fr.on.mange.quoi.organizer.domain.model.choice;

import java.util.Optional;

public class RecipeCategoryChoiceOrganizer {

    private Optional<String> optRecipeCategoryId =  Optional.empty();

    private String label;

    public RecipeCategoryChoiceOrganizer(String categoryId, String label) {
        this.optRecipeCategoryId = Optional.of(categoryId);
        this.label = label;
    }

    public Optional<String> optRecipeCategoryId() {
        return optRecipeCategoryId;
    }

    public String label() {
        return label;
    }
}

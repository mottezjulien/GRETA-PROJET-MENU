package fr.on.mange.quoi.organizer.domain.model.choice;

import fr.on.mange.quoi.tools.StringTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecipeCategoriesChoiceOrganizer implements ChoiceOrganizer {

    private Optional<String> optId = Optional.empty();

    private List<RecipeCategoryChoiceOrganizer> categories = new ArrayList<>();

    private Optional<RecipeCategoriesChoicePreparationOrganizer> optPreparation = Optional.empty();

    public RecipeCategoriesChoiceOrganizer() {
    }

    public RecipeCategoriesChoiceOrganizer(String id) {
        this.optId = Optional.of(id);
    }

    public RecipeCategoriesChoiceOrganizer(RecipeCategoriesChoicePreparationOrganizer preparation) {
        this.optPreparation = Optional.of(preparation);
    }

    public RecipeCategoriesChoiceOrganizer(String id, RecipeCategoriesChoicePreparationOrganizer preparation) {
        this.optId = Optional.of(id);
        this.optPreparation = Optional.of(preparation);
    }

    public Optional<String> getOptId() {
        return optId;
    }

    public void insert(RecipeCategoryChoiceOrganizer category) {
        categories.add(category);
    }

    public List<RecipeCategoryChoiceOrganizer> categories() {
        return new ArrayList<>(categories);
    }



    public Optional<RecipeCategoriesChoicePreparationOrganizer> optPreparation() {
        return optPreparation;
    }

    public String label() {
        return StringTools.separateWithComma(categories, RecipeCategoryChoiceOrganizer::label);
    }
}

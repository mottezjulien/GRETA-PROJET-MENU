package fr.on.mange.quoi.organizer.persistence.entity;

import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoriesChoicePreparationOrganizer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@DiscriminatorValue("RECIPE_CATEGORIES")
public class RecipeCategoriesChoiceOrganizerEntity extends ChoiceOrganizerEntity {

    @ElementCollection
    @CollectionTable(name ="ORGANIZER_CHOICE_RECIPE_CATEGORY")
    private List<String> recipeCategoryIds = new ArrayList<>();

    private RecipeCategoriesChoicePreparationOrganizer preparation;

    public List<String> getRecipeCategoryIds() {
        return recipeCategoryIds;
    }

    public void setRecipeCategoryIds(List<String> recipeCategoryIds) {
        this.recipeCategoryIds = recipeCategoryIds;
    }

    public RecipeCategoriesChoicePreparationOrganizer getPreparation() {
        return preparation;
    }

    public void setPreparation(RecipeCategoriesChoicePreparationOrganizer preparation) {
        this.preparation = preparation;
    }
}

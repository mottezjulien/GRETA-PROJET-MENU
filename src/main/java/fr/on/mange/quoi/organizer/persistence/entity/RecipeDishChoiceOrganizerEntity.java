package fr.on.mange.quoi.organizer.persistence.entity;

import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@DiscriminatorValue("RECIPE_DISH")
public class RecipeDishChoiceOrganizerEntity extends ChoiceOrganizerEntity {

    private String recipeDishId;

    public String getRecipeDishId() {
        return recipeDishId;
    }

    public void setRecipeDishId(String recipeDishId) {
        this.recipeDishId = recipeDishId;
    }
}

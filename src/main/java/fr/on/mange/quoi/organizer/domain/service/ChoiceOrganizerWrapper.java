package fr.on.mange.quoi.organizer.domain.service;

import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.generic.facade.IdLabelDTO;
import fr.on.mange.quoi.organizer.domain.model.choice.ChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoriesChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoryChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeDishChoiceOrganizer;
import fr.on.mange.quoi.organizer.persistence.entity.ChoiceOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.RecipeCategoriesChoiceOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.RecipeDishChoiceOrganizerEntity;
import fr.on.mange.quoi.recipe.facade.adapter.RecipeExternalAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChoiceOrganizerWrapper {

    @Autowired
    private RecipeExternalAdapter recipeAdapter;

    public ChoiceOrganizer fromEntity(ChoiceOrganizerEntity entity) throws ApplicationCommunicationException {
        if(entity instanceof RecipeDishChoiceOrganizerEntity) {
            return recipeDishChoice((RecipeDishChoiceOrganizerEntity)entity);
        }
        if(entity instanceof RecipeCategoriesChoiceOrganizerEntity) {
            return recipeCategoriesChoice((RecipeCategoriesChoiceOrganizerEntity)entity);
        }
        throw new IllegalArgumentException();
    }

    private RecipeDishChoiceOrganizer recipeDishChoice(RecipeDishChoiceOrganizerEntity entity) throws ApplicationCommunicationException {
        Optional<IdLabelDTO> optDish = recipeAdapter.findDishById(entity.getRecipeDishId());
        if(optDish.isPresent()) {
            return new RecipeDishChoiceOrganizer(optDish.get().getId(), optDish.get().getLabel());
        }
        throw new ApplicationCommunicationException("Recipe dish with id " + entity.getRecipeDishId() + " not found");

    }

    private RecipeCategoriesChoiceOrganizer recipeCategoriesChoice(RecipeCategoriesChoiceOrganizerEntity entity) throws ApplicationCommunicationException {
        RecipeCategoriesChoiceOrganizer choice = new RecipeCategoriesChoiceOrganizer(entity.getId());
        if(entity.getPreparation() != null) {
            choice = new RecipeCategoriesChoiceOrganizer(entity.getId(), entity.getPreparation());
        }
        for(String categoryId :  entity.getRecipeCategoryIds()) {
            Optional<IdLabelDTO> optCategory = recipeAdapter.findCategoryById(categoryId);
            if(optCategory.isEmpty()) {
                throw new ApplicationCommunicationException("Recipe category with id " + categoryId + " not found");
            }
            choice.insert(new RecipeCategoryChoiceOrganizer(optCategory.get().getId(), optCategory.get().getLabel()));
        }
        return choice;
    }

}

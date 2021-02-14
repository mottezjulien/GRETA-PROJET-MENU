package fr.on.mange.quoi.organizer.domain.service;

import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.generic.facade.IdLabelDTO;
import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.ChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoriesChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoryChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeDishChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.day.Day;
import fr.on.mange.quoi.organizer.domain.model.day.DayNoMatter;
import fr.on.mange.quoi.organizer.domain.model.day.DayOfWeek;
import fr.on.mange.quoi.organizer.persistence.entity.*;
import fr.on.mange.quoi.recipe.facade.adapter.RecipeExternalAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DayOrganizerWrapper {

    @Autowired
    private ChoiceOrganizerWrapper choiceAdapter;

    public DayOrganizer fromEntity(DayOrganizerEntity entity) throws ApplicationCommunicationException {
        return new DayOrganizer(Optional.of(entity.getId()), day(entity.getDayType()), choicesByMeal(entity.getChoices()));
    }

    private Day day(DayTypeOrganizerEntity entity) {
        switch (entity) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
            case SATURDAY:
            case SUNDAY:
                return new DayOfWeek(entity.name());
            default:
                return new DayNoMatter();
        }
    }

    private Map<MealOrganizer, ChoiceOrganizer> choicesByMeal(Set<ChoiceOrganizerEntity> choices) throws ApplicationCommunicationException {
        Map<MealOrganizer, ChoiceOrganizer> map = new HashMap<>();
        for (ChoiceOrganizerEntity choice : choices) {
            map.put(orEmpty(choice.getMeal()), choiceAdapter.fromEntity(choice));
        }
        return map;
    }

    private MealOrganizer orEmpty(MealOrganizer meal) {
        if(meal == null){
            return MealOrganizer.ANYONE;
        }
        return meal;
    }


}

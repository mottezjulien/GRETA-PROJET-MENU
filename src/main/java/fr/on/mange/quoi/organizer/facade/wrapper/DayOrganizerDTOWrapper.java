package fr.on.mange.quoi.organizer.facade.wrapper;

import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.ChoiceOrganizer;
import fr.on.mange.quoi.organizer.facade.dto.DayOrganizerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DayOrganizerDTOWrapper {

    @Autowired
    private MealOrganizerDTOWrapper mealWrapper;

    public DayOrganizerDTO fromModel(DayOrganizer model) {
        DayOrganizerDTO dto = new DayOrganizerDTO();
        dto.setId(model.getOptId().get());
        dto.setDay(model.getDayValue());
        Map<MealOrganizer, ChoiceOrganizer> choiceByType = model.choiceByType();
        List<MealOrganizer> orderKey = mealsOrderByType(choiceByType);
        for (MealOrganizer type : orderKey) {
            dto.getMeals().add(mealWrapper.fromModel(type, choiceByType.get(type)));
        }
        return dto;
    }

    private List<MealOrganizer> mealsOrderByType(Map<MealOrganizer, ChoiceOrganizer> choiceByType) {
        List<MealOrganizer> orderKey = new ArrayList<>(choiceByType.keySet());
        Collections.sort(orderKey, Comparator.comparingInt(Enum::ordinal));
        return orderKey;
    }
}

package fr.on.mange.quoi.organizer.facade.wrapper;


import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.ChoiceOrganizer;
import fr.on.mange.quoi.organizer.facade.dto.MealOrganizerDTO;
import org.springframework.stereotype.Component;

@Component
public class MealOrganizerDTOWrapper {

    public MealOrganizerDTO fromModel(MealOrganizer type, ChoiceOrganizer choice) {
        MealOrganizerDTO mealDTO = new MealOrganizerDTO();
        mealDTO.setTypeLabel(type.labelFr());
        mealDTO.setChoiceLabel(choice.label());
        return mealDTO;
    }

}

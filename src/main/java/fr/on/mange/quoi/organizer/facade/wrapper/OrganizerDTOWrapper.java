package fr.on.mange.quoi.organizer.facade.wrapper;

import fr.on.mange.quoi.generic.facade.IdLabelDTO;
import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.Organizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoryChoiceOrganizer;
import fr.on.mange.quoi.organizer.facade.dto.DayOrganizerDTO;
import fr.on.mange.quoi.organizer.facade.dto.OrganizerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class OrganizerDTOWrapper {

    @Autowired
    private DayOrganizerDTOWrapper itemWrapper;

    public OrganizerDTO fromModel(Organizer model) {
        OrganizerDTO dto = new OrganizerDTO();
        dto.setLabel(model.getLabel());
        dto.setUserId(model.getOptUserId().get());

        dto.setMonday(findOrCreateByDayOfWeek(model.days(), DayOfWeek.MONDAY));
        dto.setTuesday(findOrCreateByDayOfWeek(model.days(), DayOfWeek.TUESDAY));
        dto.setWednesday(findOrCreateByDayOfWeek(model.days(), DayOfWeek.WEDNESDAY));
        dto.setThursday(findOrCreateByDayOfWeek(model.days(), DayOfWeek.THURSDAY));
        dto.setFriday(findOrCreateByDayOfWeek(model.days(), DayOfWeek.FRIDAY));
        dto.setSaturday(findOrCreateByDayOfWeek(model.days(), DayOfWeek.SATURDAY));
        dto.setSunday(findOrCreateByDayOfWeek(model.days(), DayOfWeek.SUNDAY));

        dto.setNoMatterDay(findOrCreate(model.days(), meal -> meal.isNoMatterDay()));

        return dto;
    }

    private DayOrganizerDTO findOrCreateByDayOfWeek(List<DayOrganizer> meals, DayOfWeek dayOfWeek) {
        return findOrCreate(meals, meal -> meal.isSameDay(dayOfWeek));
    }


    private DayOrganizerDTO findOrCreate(List<DayOrganizer> meals, Predicate<DayOrganizer> dayOrganizerPredicate) {
        return meals.stream()
                .filter(dayOrganizerPredicate)
                .findAny()
                .map(meal -> itemWrapper.fromModel(meal))
                .orElse(new DayOrganizerDTO());
    }
}

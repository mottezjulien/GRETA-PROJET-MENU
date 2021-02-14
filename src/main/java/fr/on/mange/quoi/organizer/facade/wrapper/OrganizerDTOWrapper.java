package fr.on.mange.quoi.organizer.facade.wrapper;

import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.Organizer;
import fr.on.mange.quoi.organizer.facade.dto.DayOrganizerDTO;
import fr.on.mange.quoi.organizer.facade.dto.OrganizerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.List;
import java.util.function.Predicate;

@Component
public class OrganizerDTOWrapper {

    @Autowired
    private DayOrganizerDTOWrapper itemWrapper;

    public OrganizerDTO fromModel(Organizer model) {
        OrganizerDTO dto = new OrganizerDTO();
        dto.setLabel(model.getLabel());

        dto.setMonday(findOrCreateByDayOfWeek(model.meals(), DayOfWeek.MONDAY));
        dto.setTuesday(findOrCreateByDayOfWeek(model.meals(), DayOfWeek.TUESDAY));
        dto.setWednesday(findOrCreateByDayOfWeek(model.meals(), DayOfWeek.WEDNESDAY));
        dto.setThursday(findOrCreateByDayOfWeek(model.meals(), DayOfWeek.THURSDAY));
        dto.setFriday(findOrCreateByDayOfWeek(model.meals(), DayOfWeek.FRIDAY));
        dto.setSaturday(findOrCreateByDayOfWeek(model.meals(), DayOfWeek.SATURDAY));
        dto.setSunday(findOrCreateByDayOfWeek(model.meals(), DayOfWeek.SUNDAY));

        dto.setNoMatterDay(findOrCreate(model.meals(), meal -> meal.isNoMatterDay()));

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

package fr.on.mange.quoi.organizer.domain.service;

import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.ChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.day.Day;
import fr.on.mange.quoi.organizer.domain.model.day.DayNoMatter;
import fr.on.mange.quoi.organizer.domain.model.day.DayOfWeekModel;
import fr.on.mange.quoi.organizer.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.*;

@Component
public class DayOrganizerWrapper {

    @Autowired
    private ChoiceOrganizerWrapper choiceWrapper;

    public DayOrganizer fromEntity(DayOrganizerEntity entity) throws ApplicationCommunicationException {
        return new DayOrganizer(Optional.of(entity.getId()), fromEntity(entity.getDayType()), choicesByMeal(entity.getChoices()));
    }

    /*public DayOrganizerEntity toEntity(DayOrganizer model, OrganizerEntity organizerEntity) throws ApplicationCommunicationException {
        DayOrganizerEntity entity = new DayOrganizerEntity();
        entity.setId(model.getOptId().orElse(null));
        entity.setOrganizer(organizerEntity);
        entity.setDayType(model.toDayOfWeek()
                .map(dayOfWeek -> toEntity(dayOfWeek))
                .orElse(DayTypeOrganizerEntity.NO_MATTER));

        for ( MealOrganizer meal : model.choiceByType().keySet()) {
            entity.getChoices().add(choiceWrapper.toEntity(meal, model.choiceByType().get(meal)));
        }
        return entity;
    }*/

    public DayOrganizer fromEntityWithDay(DayOrganizerEntity entity) throws ApplicationCommunicationException {
        Day day = fromEntity(entity.getDayType());
        DayOrganizer dayOrganizer = new DayOrganizer(Optional.of(entity.getId()), day, choicesByMeal(entity.getChoices()));
        //dayOrganizer.setDayValue(String.valueOf(entity.getDayType()));
        return  dayOrganizer;
    }

    private Day fromEntity(DayTypeOrganizerEntity entity) {
        switch (entity) {
            case NO_MATTER:
                return new DayNoMatter();
            default:
                return new DayOfWeekModel(DayOfWeek.valueOf(entity.name()));
        }
    }

    private DayTypeOrganizerEntity toEntity(DayOfWeekModel model) {
        return DayTypeOrganizerEntity.valueOf(model.toJavaTime().name());
    }

    private Map<MealOrganizer, ChoiceOrganizer> choicesByMeal(Set<ChoiceOrganizerEntity> choices) throws ApplicationCommunicationException {
        Map<MealOrganizer, ChoiceOrganizer> map = new HashMap<>();
        for (ChoiceOrganizerEntity choice : choices) {
            map.put(orEmpty(choice.getMeal()), choiceWrapper.fromEntity(choice));
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

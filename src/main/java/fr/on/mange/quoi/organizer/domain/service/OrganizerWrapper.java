package fr.on.mange.quoi.organizer.domain.service;

import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.Organizer;
import fr.on.mange.quoi.organizer.persistence.entity.DayOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrganizerWrapper {

    @Autowired
    private DayOrganizerWrapper dayWrapper;

    public Organizer fromEntity(OrganizerEntity entity) throws ApplicationCommunicationException {
        Organizer model = new Organizer();
        model.setOptId(Optional.of(entity.getId()));
        model.setLabel(entity.getLabel());
        model.setOptUserId(Optional.ofNullable(entity.getUserId()));
        for (DayOrganizerEntity day: entity.getDays()) {
            model.insert(dayWrapper.fromEntity(day));
        }
        return model;
    }

    /*public OrganizerEntity toEntity(Organizer model) throws ApplicationCommunicationException {
        OrganizerEntity entity = new OrganizerEntity();
        entity.setId(model.getOptId().orElse(null));
        entity.setUserId(model.getOptUserId().get());
        entity.setLabel(model.getLabel());
        entity.setUserId(model.getOptUserId().get());

        for ( DayOrganizer day : model.getDays()) {
            entity.getDays().add(dayWrapper.toEntity(day))
        }
        return entity;
    }*/

    public List<Organizer> fromEntities(List<OrganizerEntity> listEntity) throws ApplicationCommunicationException {
        List<Organizer> organizers = new ArrayList<>();
        for(OrganizerEntity entity : listEntity){
            organizers.add(fromEntity(entity));
        }
        return organizers;
    }
}

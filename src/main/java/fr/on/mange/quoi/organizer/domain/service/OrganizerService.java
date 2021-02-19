package fr.on.mange.quoi.organizer.domain.service;

import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.generic.exception.ApplicationServiceException;
import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.Organizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoryChoiceOrganizer;
import fr.on.mange.quoi.organizer.persistence.entity.*;
import fr.on.mange.quoi.organizer.persistence.repository.ChoiceOrganizerRepository;
import fr.on.mange.quoi.organizer.persistence.repository.DayOrganizerRepository;
import fr.on.mange.quoi.organizer.persistence.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrganizerService {
    @Autowired
    private DayOrganizerRepository dayOrganizerRepository;

    @Autowired
    private OrganizerRepository repository;

    @Autowired
    private OrganizerWrapper wrapper;

    @Autowired
    private DayOrganizerRepository dayRepository;

    @Autowired
    private ChoiceOrganizerRepository choiceRepository;

    /*public Organizer get(String id) throws ApplicationServiceException {
        Optional<OrganizerEntity> optEntity = repository.findById(id);
        if (optEntity.isPresent()) {
            try {
                return wrapper.fromEntity(optEntity.get());
            } catch (ApplicationCommunicationException e) {
                throw new ApplicationServiceException(e);
            }
        }
        throw new ApplicationServiceException("Organisation with id " + id + " not found");
    }*/


    public Organizer findAny() throws ApplicationServiceException {
        Optional<OrganizerEntity> optEntity = repository.findAllFetchAll()
                .stream()
                .findAny();
        if (optEntity.isPresent()) {
            try {
                return wrapper.fromEntity(optEntity.get());
            } catch (ApplicationCommunicationException e) {
                throw new ApplicationServiceException(e);
            }

        }
        throw new ApplicationServiceException("Any organisation found");
    }

    public Organizer findByUserId(String userId) throws ApplicationServiceException {
        List<OrganizerEntity> entities = repository.findAllByUserId(userId);
        Optional<OrganizerEntity> findFirstOptional = entities.stream().findFirst();
        try {
            if(findFirstOptional.isPresent()) {
                return wrapper.fromEntity(findFirstOptional.get());
            }
            throw new ApplicationServiceException("Organizer not found");
        } catch (ApplicationCommunicationException e) {
            throw new ApplicationServiceException(e);
        }
    }

    public Organizer findByLabel(String label) throws ApplicationServiceException {
        Optional<OrganizerEntity> optEntity = repository.findByLabel(label);

        if(optEntity.isPresent()){
            try {
                return wrapper.fromEntity(optEntity.get());
            } catch (ApplicationCommunicationException e) {
                throw new ApplicationServiceException(e);
            }
        }
        throw new ApplicationServiceException("Organizer not found");
    }


    public List<Organizer> findAllByUserId(String UserId) throws ApplicationServiceException {
        try {
            return wrapper.fromEntities(repository.findAllByUserId(UserId));
        } catch (ApplicationCommunicationException e) {
            throw new ApplicationServiceException(e);
        }
    }

    public void initDays(OrganizerEntity organizerEntity) {

        for (DayTypeOrganizerEntity day : DayTypeOrganizerEntity.values()) {
            DayOrganizerEntity entity = dayRepository.save(createOrgaDay(organizerEntity,day));
            initChoice(entity);
        }
    }

    private void initChoice(DayOrganizerEntity entity) {
        RecipeCategoriesChoiceOrganizerEntity choice = new RecipeCategoriesChoiceOrganizerEntity();
        choice.setDay(entity);
        choice.setMeal(MealOrganizer.ANYONE);

        choiceRepository.save(choice);
    }

    private DayOrganizerEntity createOrgaDay(OrganizerEntity organizerEntity, DayTypeOrganizerEntity day) {
        DayOrganizerEntity dayOrganizerEntity = new DayOrganizerEntity();
        dayOrganizerEntity.setDayType(day);
        dayOrganizerEntity.setOrganizer(organizerEntity);
        return dayOrganizerEntity;
    }


}

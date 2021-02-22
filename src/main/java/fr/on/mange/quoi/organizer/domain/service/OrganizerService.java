package fr.on.mange.quoi.organizer.domain.service;

import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.generic.exception.ApplicationServiceException;
import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.Organizer;
import fr.on.mange.quoi.organizer.domain.model.choice.ChoiceOrganizerTemplateFactory;
import fr.on.mange.quoi.organizer.domain.model.choice.ChoiceOrganizerTemplateFamilyFactory;
import fr.on.mange.quoi.organizer.domain.model.choice.ChoiceOrganizerTemplateStudentFactory;
import fr.on.mange.quoi.organizer.facade.dto.ChoiceOrganizerTemplateDTO;
import fr.on.mange.quoi.organizer.persistence.entity.*;
import fr.on.mange.quoi.organizer.persistence.repository.ChoiceOrganizerRepository;
import fr.on.mange.quoi.organizer.persistence.repository.DayOrganizerRepository;
import fr.on.mange.quoi.organizer.persistence.repository.OrganizerRepository;
import fr.on.mange.quoi.user.facade.dto.UserIdDTO;
import fr.on.mange.quoi.user.facade.dto.UserIdDTOWrapper;
import fr.on.mange.quoi.user.persistance.UserEntity;
import fr.on.mange.quoi.user.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {

    private static final String ORGA_STUDENT = "student";
    private static final String USER_ROLE = "USER";


    @Autowired
    private OrganizerRepository repository;

    @Autowired
    private OrganizerWrapper wrapper;

    @Autowired
    private DayOrganizerRepository dayRepository;

    @Autowired
    private ChoiceOrganizerRepository choiceRepository;

    @Autowired
    private ChoiceOrganizerTemplateFamilyFactory familyFactory;

    @Autowired
    private ChoiceOrganizerTemplateStudentFactory studentFactory;

    @Autowired
    private UserRepository userRepository;

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

    public void createFromTemplate(ChoiceOrganizerTemplateDTO choiceOrganizerTemplateDTO) throws ApplicationCommunicationException {
        ChoiceOrganizerTemplateFactory factory = findFactory(choiceOrganizerTemplateDTO);
        Organizer organizer = factory.build();
        try {
            organizer.setOptUserId(findUserId());
            saveAll(organizer);
        } catch (ApplicationServiceException e) {
            e.printStackTrace();
        }
    }

    private Optional<String> findUserId() throws ApplicationServiceException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (isConnected(auth)) {
            Optional<UserEntity> userEntity = userRepository.findByLogin(auth.getName());
            if(userEntity.isPresent()){
                return Optional.of(userEntity.get().getId());
            }
            throw new ApplicationServiceException("User not found");

        }
        throw new ApplicationServiceException("User not connected");
    }

    private ChoiceOrganizerTemplateFactory findFactory(ChoiceOrganizerTemplateDTO choiceOrganizerTemplateDTO) {
        if (choiceOrganizerTemplateDTO.getLabel().equals(ORGA_STUDENT)) {
            return studentFactory;
        }
        return familyFactory;
    }

    private void saveAll(Organizer organizer) {
        try {
            repository.save(wrapper.toEntity(organizer));

        } catch (ApplicationCommunicationException e) {
            e.printStackTrace();
        }
    }

    private boolean isConnected(Authentication auth) {
        return auth.getAuthorities().contains(new SimpleGrantedAuthority(USER_ROLE));
    }
  
    public Organizer findById(String organizerId) throws ApplicationServiceException {
        Optional<OrganizerEntity> optEntity = repository.findById(organizerId);
        if(optEntity.isPresent()){
            try {
                return wrapper.fromEntity(optEntity.get());
            } catch (ApplicationCommunicationException e) {
                throw new ApplicationServiceException(e);
            }
        }
        throw new ApplicationServiceException("Organizer not found");
    }
}

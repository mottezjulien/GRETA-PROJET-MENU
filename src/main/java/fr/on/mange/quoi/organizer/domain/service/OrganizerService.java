package fr.on.mange.quoi.organizer.domain.service;

import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.generic.exception.ApplicationServiceException;
import fr.on.mange.quoi.organizer.domain.model.Organizer;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository repository;

    @Autowired
    private OrganizerWrapper wrapper;

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
}

package fr.on.mange.quoi.organizer.domain.service;

import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.generic.exception.ApplicationServiceException;
import fr.on.mange.quoi.organizer.domain.model.Organizer;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository repository;

    @Autowired
    private OrganizerWrapper wrapper;

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
}

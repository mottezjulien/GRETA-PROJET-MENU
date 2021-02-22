package fr.on.mange.quoi.user.facade.dto;

import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.user.persistance.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserIdDTOWrapper {
    public UserIdDTO fromEntity(Optional<UserEntity> entity) throws ApplicationCommunicationException {
        if(entity.isPresent()){
            return new UserIdDTO(entity.get().getId(),entity.get().getDefaultOrganizerId());
        }
        throw new ApplicationCommunicationException("User not found");

    }
}

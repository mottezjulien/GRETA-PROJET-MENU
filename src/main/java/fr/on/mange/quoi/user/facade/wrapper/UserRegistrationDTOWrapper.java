package fr.on.mange.quoi.user.facade.wrapper;

import fr.on.mange.quoi.user.facade.dto.UserRegistrationDTO;
import fr.on.mange.quoi.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationDTOWrapper {
    public User fromDTO(UserRegistrationDTO dto){
        return new User(dto);
    }
}

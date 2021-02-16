package fr.on.mange.quoi.user.facade.wrapper;

import fr.on.mange.quoi.user.facade.dto.UserRegistrationDTO;
import fr.on.mange.quoi.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRegistrationDTOWrapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User fromDTO(UserRegistrationDTO dto){
        User user = new User();
        user.setOptId(Optional.empty());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return user;
    }
}

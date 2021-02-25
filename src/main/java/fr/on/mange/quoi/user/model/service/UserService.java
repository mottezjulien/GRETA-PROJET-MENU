package fr.on.mange.quoi.user.model.service;

import fr.on.mange.quoi.generic.exception.ApplicationServiceException;
import fr.on.mange.quoi.user.facade.dto.UserRegistrationDTO;
import fr.on.mange.quoi.user.facade.wrapper.UserRegistrationDTOWrapper;
import fr.on.mange.quoi.user.model.User;
import fr.on.mange.quoi.user.model.wrapper.UserWrapper;
import fr.on.mange.quoi.user.persistance.UserEntity;
import fr.on.mange.quoi.user.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class UserService {

    private static final String LOGIN_AUTO_USER = "autoUser";

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserWrapper wrapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveNewUser(User user) {
        return wrapper.fromEntity(repository.save(wrapper.toEntity(user)));
    }

    public void saveNewDefaultOrganizer(String login, String organizerId) throws ApplicationServiceException {
        Optional<UserEntity> optUserEntity = repository.findByLogin(login);
        if(optUserEntity.isPresent()){
            UserEntity userEntity = optUserEntity.get();
            userEntity.setDefaultOrganizerId(organizerId);
            repository.save(userEntity);
        }else {
            throw new ApplicationServiceException("User not found");
        }
    }

    public User createAutoConnectionUser() {
        UserEntity autoUser = new UserEntity();
        autoUser.setFirstname("autoFirstName");
        autoUser.setLastname("autoLastName");
        autoUser.setLogin(LOGIN_AUTO_USER);
        autoUser.setEmail("autoUser@Gmail.com");
        autoUser.setPassword(passwordEncoder.encode("123"));
        return wrapper.fromEntity(repository.save(autoUser));
    }

    public boolean isExistAutoConnectionUser() {
        return repository.findByLogin(LOGIN_AUTO_USER).isPresent();
    }
}

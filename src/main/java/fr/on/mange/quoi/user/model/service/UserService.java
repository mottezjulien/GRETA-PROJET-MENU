package fr.on.mange.quoi.user.model.service;

import fr.on.mange.quoi.generic.exception.ApplicationServiceException;
import fr.on.mange.quoi.user.model.User;
import fr.on.mange.quoi.user.model.wrapper.UserWrapper;
import fr.on.mange.quoi.user.persistance.UserEntity;
import fr.on.mange.quoi.user.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserWrapper wrapper;

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
}

package fr.on.mange.quoi.user.model.service;

import fr.on.mange.quoi.user.model.User;
import fr.on.mange.quoi.user.model.wrapper.UserWrapper;
import fr.on.mange.quoi.user.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserWrapper wrapper;

    public User saveNewUser(User user){
        return wrapper.fromEntity(repository.save(wrapper.toEntity(user)));
    }
}

package fr.on.mange.quoi.user.model.wrapper;

import fr.on.mange.quoi.user.model.User;
import fr.on.mange.quoi.user.persistance.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserWrapper {
    public UserEntity toEntity(User model){
        UserEntity entity = new UserEntity();

        if(model.getOptId().isPresent()){
            entity.setId(model.getOptId().get());
        }
        entity.setFirstname(model.getFirstname());
        entity.setLastname(model.getLastname());
        entity.setLogin(model.getLogin());
        entity.setPassword(model.getPassword());
        entity.setEmail(model.getEmail());

        return entity;
    }

    public User fromEntity(UserEntity entity){
        User model = new User();
        model.setOptId(Optional.of(entity.getId()));
        model.setLogin(entity.getLogin());
        model.setFirstname(entity.getFirstname());
        model.setLastname(entity.getLastname());
        model.setEmail(entity.getEmail());
        model.setPassword(entity.getPassword());

        return model;
    }
}

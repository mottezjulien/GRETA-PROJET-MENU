package fr.on.mange.quoi.user.model;

import fr.on.mange.quoi.user.facade.dto.UserRegistrationDTO;

import java.util.Optional;

public class User {
    private Optional<String> optId;

    private String firstname;
    private String lastname;
    private String login;
    private String email;
    private String password;

    public User(UserRegistrationDTO dto) {
        this.firstname= dto.getFirstname();
        this.lastname=dto.getLastname();
        this.login= dto.getLogin();
        this.email= dto.getEmail();
        this.password= dto.getPassword();
    }
}

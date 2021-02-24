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

    public Optional<String> getOptId() {
        return optId;
    }

    public void setOptId(Optional<String> optId) {
        this.optId = optId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

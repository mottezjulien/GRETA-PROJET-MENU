package fr.on.mange.quoi.user.facade.dto;

public class UserRegistrationDTO {
    private String firstname;
    private String lastname;
    private String login;
    private String email;
    private String password;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

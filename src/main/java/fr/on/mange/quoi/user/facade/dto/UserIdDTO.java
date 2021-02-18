package fr.on.mange.quoi.user.facade.dto;

public class UserIdDTO {
    private String uuid;

    public UserIdDTO(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}

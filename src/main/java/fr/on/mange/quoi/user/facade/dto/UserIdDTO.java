package fr.on.mange.quoi.user.facade.dto;

public class UserIdDTO {

    private String uuid;

    private String organizerId;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    public UserIdDTO(String uuid, String defaultOrganizerId) {
        this.uuid = uuid;
        this.organizerId = defaultOrganizerId;
    }

    public String getUuid() {
        return uuid;
    }

}

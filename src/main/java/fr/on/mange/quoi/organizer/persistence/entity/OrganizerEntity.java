package fr.on.mange.quoi.organizer.persistence.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ORGANIZER")
public class OrganizerEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "user_id")
    private String userId;

    private String label;

    @OneToMany(mappedBy = "organizer", fetch = FetchType.LAZY)
    private Set<DayOrganizerEntity> days = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<DayOrganizerEntity> getDays() {
        return days;
    }

    public void setDays(Set<DayOrganizerEntity> days) {
        this.days = days;
    }
}

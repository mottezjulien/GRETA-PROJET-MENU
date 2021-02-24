package fr.on.mange.quoi.organizer.persistence.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ORGANIZER_DAY")
public class DayOrganizerEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private OrganizerEntity organizer;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_type")
    private DayTypeOrganizerEntity dayType;

    @OneToMany(mappedBy = "day", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ChoiceOrganizerEntity> choices = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrganizerEntity getOrganizer() {
        return organizer;
    }

    public void setOrganizer(OrganizerEntity organizer) {
        this.organizer = organizer;
    }

    public DayTypeOrganizerEntity getDayType() {
        return dayType;
    }

    public void setDayType(DayTypeOrganizerEntity dayType) {
        this.dayType = dayType;
    }

    public Set<ChoiceOrganizerEntity> getChoices() {
        return choices;
    }

    public void setChoices(Set<ChoiceOrganizerEntity> choices) {
        this.choices = choices;
    }
}

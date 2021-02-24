package fr.on.mange.quoi.menu.persistence;

import fr.on.mange.quoi.organizer.persistence.entity.DayOrganizerEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MENU")
public class MenuEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String dateDebut;

    private String dateFin;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private Set<DayMenuEntity> days = new HashSet<>();


    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<DayMenuEntity> getDays() {
        return days;
    }

    public void setDays(Set<DayMenuEntity> days) {
        this.days = days;
    }
}

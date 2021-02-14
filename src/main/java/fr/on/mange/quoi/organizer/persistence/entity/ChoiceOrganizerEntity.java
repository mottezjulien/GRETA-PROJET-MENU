package fr.on.mange.quoi.organizer.persistence.entity;

import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ORGANIZER_CHOICE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "choice_type")
public abstract class ChoiceOrganizerEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id")
    private DayOrganizerEntity day;

    @Enumerated(EnumType.STRING)
    private MealOrganizer meal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DayOrganizerEntity getDay() {
        return day;
    }

    public void setDay(DayOrganizerEntity day) {
        this.day = day;
    }

    public MealOrganizer getMeal() {
        return meal;
    }

    public void setMeal(MealOrganizer meal) {
        this.meal = meal;
    }
}

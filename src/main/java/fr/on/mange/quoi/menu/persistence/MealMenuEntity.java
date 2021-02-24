package fr.on.mange.quoi.menu.persistence;

import fr.on.mange.quoi.menu.MealMenu;
import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "MENU_MEAL")
public class MealMenuEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id")
    private DayMenuEntity day;

    @Enumerated(EnumType.STRING)
    private MealMenu meal;

    private String recipeDishId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DayMenuEntity getDay() {
        return day;
    }

    public void setDay(DayMenuEntity day) {
        this.day = day;
    }

    public MealMenu getMeal() {
        return meal;
    }

    public void setMeal(MealMenu meal) {
        this.meal = meal;
    }

    public String getRecipeDishId() {
        return recipeDishId;
    }

    public void setRecipeDishId(String recipeDishId) {
        this.recipeDishId = recipeDishId;
    }
}

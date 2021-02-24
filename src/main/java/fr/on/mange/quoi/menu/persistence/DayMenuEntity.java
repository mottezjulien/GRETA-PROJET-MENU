package fr.on.mange.quoi.menu.persistence;

import fr.on.mange.quoi.organizer.persistence.entity.ChoiceOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MENU_DAY")
public class DayMenuEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;

    @OneToMany(mappedBy = "day", fetch = FetchType.LAZY)
    private Set<MealMenuEntity> meals = new HashSet<>();

    private DayTypeMenuEntity day;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    public Set<MealMenuEntity> getMeals() {
        return meals;
    }

    public void setMeals(Set<MealMenuEntity> meals) {
        this.meals = meals;
    }

    public DayTypeMenuEntity getDay() {
        return day;
    }

    public void setDay(DayTypeMenuEntity day) {
        this.day = day;
    }
}

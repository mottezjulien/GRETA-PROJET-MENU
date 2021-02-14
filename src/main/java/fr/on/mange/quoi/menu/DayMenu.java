package fr.on.mange.quoi.menu;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class DayMenu {

    private Optional<String> optId;

    private LocalDate day;

    private Map<MealMenu, RecipeDishMenu> choiceByType;

}

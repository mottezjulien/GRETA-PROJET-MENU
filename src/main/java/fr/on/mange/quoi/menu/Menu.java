package fr.on.mange.quoi.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Menu {

    private Optional<String> optId = Optional.empty();

    private Optional<String> optOrganizerId = Optional.empty();

    private List<DayMenu> meals = new ArrayList<>();

}

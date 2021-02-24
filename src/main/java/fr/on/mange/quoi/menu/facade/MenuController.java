package fr.on.mange.quoi.menu.facade;

import fr.on.mange.quoi.menu.MealMenu;
import fr.on.mange.quoi.menu.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.*;

@Controller
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RecipeDishMenuRepository dishMenuRepository;

    @GetMapping("/menu")
    public ModelAndView home() {

        MenuDTO menuDTO = new MenuDTO();

        List<MenuEntity> menus = menuRepository.findAllFetchAll();
        MenuEntity menu = menus.get(0);
        menuDTO.setDateDebut(menu.getDateDebut());
        menuDTO.setDateFin(menu.getDateFin());


        for(DayMenuEntity day: menu.getDays()) {
            List<MealMenuEntity> meals = new ArrayList(day.getMeals());
            Collections.sort(meals, Comparator.comparing(MealMenuEntity::getMeal));

            List <MenuJourDTO> list = fromDay(menuDTO, day.getDay());
            for(MealMenuEntity meal : meals) {
                RecipeDishMenuEntity dish = dishMenuRepository.findById(meal.getRecipeDishId()).get();
                MenuJourDTO menuJour = new MenuJourDTO();
                menuJour.setPlat(dish.getLabel());
                menuJour.setRepas(meal.getMeal().toString());
                list.add(menuJour);
            }
        }

        ModelAndView modelAndView = new ModelAndView("menu");
        modelAndView.addObject("menu", menuDTO);
        return modelAndView;



    }

    private List<MenuJourDTO> fromDay(MenuDTO menuDTO, DayTypeMenuEntity day) {
        switch (day) {
            case MONDAY:
                return menuDTO.getLundi();
            case TUESDAY:
                return menuDTO.getMardi();
            case WEDNESDAY:
                return menuDTO.getMercredi();
            case THURSDAY:
                return menuDTO.getJeudi();
            case FRIDAY:
                return menuDTO.getVendredi();
            case SATURDAY:
                return menuDTO.getSamedi();
            case SUNDAY:
                return menuDTO.getDimanche();
        }
        return null;
    }

}

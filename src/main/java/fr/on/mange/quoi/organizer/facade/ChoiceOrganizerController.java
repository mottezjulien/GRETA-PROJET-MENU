package fr.on.mange.quoi.organizer.facade;


import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoriesChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoryChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.service.ChoiceOrganizerWrapper;
import fr.on.mange.quoi.organizer.domain.service.DayOrganizerWrapper;
import fr.on.mange.quoi.organizer.facade.wrapper.DayOrganizerDTOWrapper;
import fr.on.mange.quoi.organizer.persistence.entity.ChoiceOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.DayOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.RecipeCategoriesChoiceOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.repository.ChoiceOrganizerRepository;
import fr.on.mange.quoi.organizer.persistence.repository.DayOrganizerRepository;
import fr.on.mange.quoi.recipe.facade.adapter.RecipeExternalAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ChoiceOrganizerController {


    @Autowired
    private DayOrganizerDTOWrapper dayDtoWrapper;

    @Autowired
    private DayOrganizerWrapper dayWrapper;

    @Autowired
    private DayOrganizerRepository dayRepository;


    @Autowired
    private ChoiceOrganizerWrapper wrapper;

    @Autowired
    private ChoiceOrganizerRepository repository;

    @Autowired
    private RecipeExternalAdapter recipeAdapter;


    @GetMapping("/addCategories")
    public ModelAndView addCategories(@RequestParam("id") String dayId) {
        ModelAndView modelAndView = new ModelAndView("addCategories");
        modelAndView.addObject("allCategories", recipeAdapter.findAllCategories());
        modelAndView.addObject("dayId", dayId);
        return modelAndView;
    }

    @PostMapping("/addCategories")
    public ModelAndView newChoiceCategories(@ModelAttribute("request") AddCategoryDTORequest request) {
        Optional<DayOrganizerEntity> entity = dayRepository.findByIdFetchAll(request.getDayId());
        if (entity.isPresent()) {
            Optional<RecipeCategoriesChoiceOrganizerEntity> optChoice = selectOneCategoriesChoice(entity.get());
            optChoice.ifPresent(choice -> insertCategory(choice, request.getCategoryId()));
        }
        return new ModelAndView("redirect:/");
    }

    //TODO:Julien:To delete when POST newChoiceCategories with choiceIdRequestParam
    private Optional<RecipeCategoriesChoiceOrganizerEntity> selectOneCategoriesChoice(DayOrganizerEntity entity) {
        for(ChoiceOrganizerEntity choice: entity.getChoices()) {
            if(choice instanceof RecipeCategoriesChoiceOrganizerEntity) {
                return Optional.of((RecipeCategoriesChoiceOrganizerEntity)choice);
            }
        }
        return Optional.empty();
    }


    //TODO:Julien:To delete when POST newChoiceCategories with choiceIdRequestParam
    private void insertCategory(RecipeCategoriesChoiceOrganizerEntity choice, String categoryId) {
        choice.getRecipeCategoryIds().add(categoryId);
        repository.save(choice);
    }


}

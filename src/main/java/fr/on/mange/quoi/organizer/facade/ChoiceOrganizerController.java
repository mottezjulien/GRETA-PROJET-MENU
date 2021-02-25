package fr.on.mange.quoi.organizer.facade;


import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.ChoiceOrganizer;
import fr.on.mange.quoi.organizer.domain.service.ChoiceOrganizerWrapper;
import fr.on.mange.quoi.organizer.domain.service.DayOrganizerService;
import fr.on.mange.quoi.organizer.domain.service.DayOrganizerWrapper;
import fr.on.mange.quoi.organizer.facade.dto.DayOrganizerDTO;
import fr.on.mange.quoi.organizer.facade.dto.MealOrganizerDTO;
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

import java.util.List;
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
    private RecipeExternalAdapter recipeAdapter;

    @Autowired
    private ChoiceOrganizerRepository choiceOrganizerRepository;

    @Autowired
    DayOrganizerService dayOrganizerService;

    @GetMapping("/addCategories")
    public ModelAndView addCategories(@RequestParam("id") String id, @RequestParam("typeLabel") String typeLabel) throws ApplicationCommunicationException {
        ModelAndView modelAndView = new ModelAndView("addCategories");
        modelAndView.addObject("allCategories", recipeAdapter.findAllCategories());
        DayOrganizerEntity dayOrganizerEntity = dayRepository.findByIdFetchAll(id).get();
        DayOrganizer dayOrganizer = dayWrapper.fromEntityWithDay(dayOrganizerEntity);
        DayOrganizerDTO dayOrganizerDTO = dayDtoWrapper.fromModel(dayOrganizer);
        modelAndView.addObject("dayCategories", dayOrganizerDTO);
        modelAndView.addObject("typeLabel", typeLabel);
        return modelAndView;
    }

    @PostMapping("/addCategories")
    public ModelAndView newCategoriesInSelectChoice(@ModelAttribute("request") AddCategoryDTORequest request) {
        Optional<DayOrganizerEntity> entity = dayRepository.findByIdFetchAll(request.getDayId());
        Optional<RecipeCategoriesChoiceOrganizerEntity> optChoice = selectCategoriesChoice(entity.get(), request.getTypeLabel());
        optChoice.ifPresent(choice -> insertCategory(choice, request.getCategoryId()));
        return new ModelAndView("redirect:/editCategories?id=" + request.getDayId());
    }

    @GetMapping("/addChoice")
    public ModelAndView addChoice(@RequestParam("id") String id) throws ApplicationCommunicationException {
        ModelAndView modelAndView = new ModelAndView("addChoice");
        DayOrganizerEntity dayOrganizerEntity = dayRepository.findByIdFetchAll(id).get();
        DayOrganizer dayOrganizer = dayWrapper.fromEntityWithDay(dayOrganizerEntity);
        DayOrganizerDTO dayOrganizerDTO = dayDtoWrapper.fromModel(dayOrganizer);
        modelAndView.addObject("dayCategories", dayOrganizerDTO);
        List<MealOrganizer> choiceList = dayOrganizerService.createListLabel();
        modelAndView.addObject("listChoice", choiceList);
        return modelAndView;
    }

    // en cours
    @PostMapping("/addChoice")
    public ModelAndView newChoice(@ModelAttribute("request") AddChoiceDTORequest request) throws ApplicationCommunicationException {
        boolean mealAlReadyExist = false;
        RecipeCategoriesChoiceOrganizerEntity recipeCategoriesChoiceOrganizerEntity = new RecipeCategoriesChoiceOrganizerEntity();
        DayOrganizerEntity dayOrganizerEntity = dayRepository.findById(request.getDayId()).get();
        DayOrganizer dayOrganizer = dayWrapper.fromEntityWithDay(dayOrganizerEntity);
        DayOrganizerDTO dayOrganizerDTO = dayDtoWrapper.fromModel(dayOrganizer);
        for (MealOrganizerDTO mealOrganizerDTO : dayOrganizerDTO.getMeals()) {
            if (mealOrganizerDTO.getTypeLabel().equalsIgnoreCase(request.getListChoice().labelFr())) {
                mealAlReadyExist = true;
                break;
            }
        }
        if (!mealAlReadyExist) {
            recipeCategoriesChoiceOrganizerEntity.setDay(dayOrganizerEntity);
            recipeCategoriesChoiceOrganizerEntity.setMeal(request.getListChoice());
            choiceOrganizerRepository.save(recipeCategoriesChoiceOrganizerEntity);
        }
        return new ModelAndView("redirect:/editCategories?id=" + request.getDayId());
    }

    @GetMapping("/editCategories")
    public ModelAndView editCategories(@RequestParam("id") String dayId) throws ApplicationCommunicationException {
        ModelAndView modelAndView = new ModelAndView("editCategories");
        DayOrganizerEntity dayOrganizerEntity = dayRepository.findByIdFetchAll(dayId).get();
        DayOrganizer dayOrganizer = dayWrapper.fromEntityWithDay(dayOrganizerEntity);
        DayOrganizerDTO dayOrganizerDTO = dayDtoWrapper.fromModel(dayOrganizer);
        modelAndView.addObject("dayCategories", dayOrganizerDTO);
        return modelAndView;
    }

    @PostMapping("/editCategories")
    public ModelAndView editCategories(@ModelAttribute("request") AddCategoryDTORequest request) {
        Optional<DayOrganizerEntity> entity = dayRepository.findByIdFetchAll(request.getDayId());
        if (entity.isPresent()) {
            Optional<RecipeCategoriesChoiceOrganizerEntity> optChoice = selectOneCategoriesChoice(entity.get());
            optChoice.ifPresent(choice -> updateCategory(choice, request.getCategoryId()));
        }
        return new ModelAndView("redirect:/organizer");
    }

    @GetMapping("/supCategories")
    public ModelAndView supCategories(@RequestParam("id") String dayId, @RequestParam("typeLabel") String typeLabel) {
        Optional<DayOrganizerEntity> entity = dayRepository.findByIdFetchAll(dayId);
        if (entity.isPresent()) {
            Optional<RecipeCategoriesChoiceOrganizerEntity> optChoice = selectCategoriesChoice(entity.get(), typeLabel);

            optChoice.ifPresent(choice -> deleteCategory(choice, dayId));
        }
        return new ModelAndView("redirect:/editCategories?id=" + dayId);
    }

    //TODO:Julien:To delete when POST newChoiceCategories with choiceIdRequestParam
    private Optional<RecipeCategoriesChoiceOrganizerEntity> selectOneCategoriesChoice(DayOrganizerEntity entity) {
        for (ChoiceOrganizerEntity choice : entity.getChoices()) {
            if (choice instanceof RecipeCategoriesChoiceOrganizerEntity) {
                return Optional.of((RecipeCategoriesChoiceOrganizerEntity) choice);
            }
        }
        return Optional.empty();
    }

    private Optional<RecipeCategoriesChoiceOrganizerEntity> selectCategoriesChoice(DayOrganizerEntity entity, String typeLabel) {
        for (ChoiceOrganizerEntity choice : entity.getChoices()) {
            if (choice.getMeal() != null) {
                if (choice.getMeal().labelFr().equalsIgnoreCase(typeLabel)) {
                    if (choice instanceof RecipeCategoriesChoiceOrganizerEntity) {
                        return Optional.of((RecipeCategoriesChoiceOrganizerEntity) choice);
                    }
                }
            }
        }
        return Optional.empty();
    }

    //TODO:Julien:To delete when POST newChoiceCategories with choiceIdRequestParam
    private void insertCategory(RecipeCategoriesChoiceOrganizerEntity choice, String categoryId) {
        choice.getRecipeCategoryIds().add(categoryId);
        choiceOrganizerRepository.save(choice);
    }

    private void updateCategory(RecipeCategoriesChoiceOrganizerEntity choice, String categoryId) {
        // if (!choice.getRecipeCategoryIds().isEmpty()) {
        choice.getRecipeCategoryIds().clear();
        choice.getRecipeCategoryIds().add(categoryId);
        choiceOrganizerRepository.save(choice);
        //  }
    }

    private void deleteCategory(RecipeCategoriesChoiceOrganizerEntity choice, String categoryId) {
        if (choice.getRecipeCategoryIds().isEmpty()) {
            choiceOrganizerRepository.deleteById(choice.getId());
          // choice.setMeal(null);
        } else {
            choice.getRecipeCategoryIds().clear();
            choiceOrganizerRepository.save(choice);
        }

    }


}

package fr.on.mange.quoi.organizer.domain.model.choice;

import fr.on.mange.quoi.generic.facade.IdLabelDTO;
import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.Organizer;
import fr.on.mange.quoi.organizer.domain.model.day.DayOfWeekModel;
import fr.on.mange.quoi.organizer.persistence.entity.*;
import fr.on.mange.quoi.recipe.facade.adapter.RecipeExternalAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.util.stream.Collectors;

@Component
public class ChoiceOrganizerTemplateFamilyFactory implements ChoiceOrganizerTemplateFactory {

    @Autowired
    private RecipeExternalAdapter recipeExternalAdapter;

    public OrganizerEntity build() {

        //--------------------------------
        OrganizerEntity organizerFamilial = new OrganizerEntity();
        organizerFamilial.setLabel("template family");
        //--------------------------------



        //----------------MONDAY-----------------------
        DayOrganizerEntity monday = new DayOrganizerEntity();
        monday.setDayType(DayTypeOrganizerEntity.MONDAY);
        monday.getChoices().add(choice(MealOrganizer.BREAK_FAST, "Compote"));
        monday.getChoices().add(choice(MealOrganizer.LUNCH, "Gratin", "Yaourt"));
        monday.getChoices().add(choice(MealOrganizer.SNACK, "Chocolat"));
        monday.getChoices().add(choice(MealOrganizer.SUPPER, "Féculant", "Yaourt"));
        organizerFamilial.getDays().add(monday);

        //----------------TUESDAY-----------------------
        DayOrganizerEntity tuesday = new DayOrganizerEntity();
        tuesday.setDayType(DayTypeOrganizerEntity.TUESDAY);
        tuesday.getChoices().add(choice(MealOrganizer.BREAK_FAST, "Céréales"));
        tuesday.getChoices().add(choice(MealOrganizer.LUNCH, "Tarte/Pizza/Cake"));
        tuesday.getChoices().add(choice(MealOrganizer.SNACK, "Yaourt"));
        tuesday.getChoices().add(choice(MealOrganizer.SUPPER, "Japonais"));
        organizerFamilial.getDays().add(tuesday);

        //----------------WEDNESDAY-----------------------
        DayOrganizerEntity wednesday = new DayOrganizerEntity();
        wednesday.setDayType(DayTypeOrganizerEntity.WEDNESDAY);
        wednesday.getChoices().add(choice(MealOrganizer.BREAK_FAST, "Yaourt", "Céréales"));
        wednesday.getChoices().add(choice(MealOrganizer.LUNCH, "Féculant"));
        wednesday.getChoices().add(choice(MealOrganizer.SNACK, "Compote"));
        wednesday.getChoices().add(choice(MealOrganizer.SUPPER, "Gratin", "Compote"));
        organizerFamilial.getDays().add(wednesday);

        //----------------THURSDAY-----------------------
        DayOrganizerEntity thursday = new DayOrganizerEntity();
        thursday.setDayType(DayTypeOrganizerEntity.THURSDAY);
        thursday.getChoices().add(choice(MealOrganizer.BREAK_FAST, "Compote"));
        thursday.getChoices().add(choice(MealOrganizer.LUNCH, "Burger"));
        thursday.getChoices().add(choice(MealOrganizer.SNACK, "Chocolat"));
        thursday.getChoices().add(choice(MealOrganizer.SUPPER, "Soupe", "Yaourt"));
        organizerFamilial.getDays().add(thursday);

        //----------------FRIDAY-----------------------
        DayOrganizerEntity friday = new DayOrganizerEntity();
        friday.setDayType(DayTypeOrganizerEntity.FRIDAY);
        friday.getChoices().add(choice(MealOrganizer.BREAK_FAST, "Céréales"));
        friday.getChoices().add(choice(MealOrganizer.LUNCH, "Féculant"));
        friday.getChoices().add(choice(MealOrganizer.SNACK, "Yaourt"));
        friday.getChoices().add(choice(MealOrganizer.SUPPER, "Tarte/Pizza/Cake", "Bière"));
        organizerFamilial.getDays().add(friday);

        //----------------SATURDAY-----------------------
        DayOrganizerEntity saturday = new DayOrganizerEntity();
        saturday.setDayType(DayTypeOrganizerEntity.SATURDAY);
        saturday.getChoices().add(choice(MealOrganizer.ANYONE, "Gratin", "Féculant", "Yaourt"));
        saturday.getChoices().add(choice(MealOrganizer.APERITIF, "Chips", "Bière"));
        organizerFamilial.getDays().add(saturday);

        //----------------SUNDAY-----------------------
        DayOrganizerEntity sunday = new DayOrganizerEntity();
        sunday.setDayType(DayTypeOrganizerEntity.SUNDAY);
        sunday.getChoices().add(choice(MealOrganizer.ANYONE, "Tarte/Pizza/Cake", "Gratin", "Compote"));
        sunday.getChoices().add(choice(MealOrganizer.SNACK, "Chocolat", "Yaourt", "Compote"));
        organizerFamilial.getDays().add(sunday);

        return organizerFamilial;
    }

    private ChoiceOrganizerEntity choice(MealOrganizer mealOrganizer, String... labels) {
        RecipeCategoriesChoiceOrganizerEntity entity = new RecipeCategoriesChoiceOrganizerEntity();
        entity.setMeal(mealOrganizer);
        for(String label:  labels) {
            IdLabelDTO idLabel = recipeExternalAdapter.findByLabel(label).get();
            entity.getRecipeCategoryIds().add(idLabel.getId());
        }
        return entity;
    }

    /*
    private RecipeCategoriesChoiceOrganizer String... labels) {
        RecipeCategoriesChoiceOrganizer categories = new RecipeCategoriesChoiceOrganizer();
        for(String label:  labels) {
            IdLabelDTO idLabel = recipeExternalAdapter.findByLabel(label).get();
            categories.getDays().add(new RecipeCategoryChoiceOrganizer(idLabel.getId(), idLabel.getLabel()));
        }
        return categories;
    }*/

}

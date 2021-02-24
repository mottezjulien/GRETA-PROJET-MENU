package fr.on.mange.quoi.organizer.domain.model.choice;


import fr.on.mange.quoi.generic.facade.IdLabelDTO;
import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.Organizer;
import fr.on.mange.quoi.organizer.persistence.entity.*;
import fr.on.mange.quoi.recipe.facade.adapter.RecipeExternalAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class ChoiceOrganizerTemplateStudentFactory implements ChoiceOrganizerTemplateFactory {

    @Autowired
    private RecipeExternalAdapter recipeExternalAdapter;

    public OrganizerEntity build() {


        //--------------------------------
        OrganizerEntity organizerStudent = new OrganizerEntity();
        organizerStudent.setLabel("template student");
        //--------------------------------

        //----------------MONDAY-----------------------
        DayOrganizerEntity monday = new DayOrganizerEntity();
        monday.setDayType(DayTypeOrganizerEntity.MONDAY);
        monday.getChoices().add(choice(MealOrganizer.BREAK_FAST, "Céréales", "Café"));
        monday.getChoices().add(choice(MealOrganizer.SUPPER, "Gratin"));
        organizerStudent.getDays().add(monday);


        //----------------TUESDAY-----------------------
        DayOrganizerEntity tuesday = new DayOrganizerEntity();
        tuesday.setDayType(DayTypeOrganizerEntity.TUESDAY);
        tuesday.getChoices().add(choice(MealOrganizer.BREAK_FAST, "Chocolat", "Café"));
        tuesday.getChoices().add(choice(MealOrganizer.SUPPER, "Burger"));
        organizerStudent.getDays().add(tuesday);

        //----------------WEDNESDAY-----------------------
        DayOrganizerEntity wednesday = new DayOrganizerEntity();
        wednesday.setDayType(DayTypeOrganizerEntity.WEDNESDAY);
        wednesday.getChoices().add(choice(MealOrganizer.BREAK_FAST, "Yaourt", "Café"));
        wednesday.getChoices().add(choice(MealOrganizer.SUPPER, "Féculant", "Compote"));
        organizerStudent.getDays().add(wednesday);

        //----------------THURSDAY-----------------------
        DayOrganizerEntity thursday = new DayOrganizerEntity();
        thursday.setDayType(DayTypeOrganizerEntity.THURSDAY);
        thursday.getChoices().add(choice(MealOrganizer.BREAK_FAST, "Compote", "Café"));
        thursday.getChoices().add(choice(MealOrganizer.SUPPER, "Tarte/Pizza/Cake", "Bière"));
        organizerStudent.getDays().add(thursday);

        //----------------FRIDAY-----------------------
        DayOrganizerEntity friday = new DayOrganizerEntity();
        friday.setDayType(DayTypeOrganizerEntity.FRIDAY);
        friday.getChoices().add(choice(MealOrganizer.BREAK_FAST, "Céréales", "Café"));
        friday.getChoices().add(choice(MealOrganizer.APERITIF, "Bière", "Chips"));
        friday.getChoices().add(choice(MealOrganizer.SUPPER, "Japonais"));
        organizerStudent.getDays().add(friday);

        //----------------SATURDAY-----------------------
        DayOrganizerEntity saturday = new DayOrganizerEntity();
        saturday.setDayType(DayTypeOrganizerEntity.SATURDAY);
        saturday.getChoices().add(choice(MealOrganizer.ANYONE, "Café", "Compote", "Féculant"));
        saturday.getChoices().add(choice(MealOrganizer.APERITIF, "Chips", "Bière", "Tarte/Pizza/Cake"));
        organizerStudent.getDays().add(saturday);

        //----------------SUNDAY-----------------------
        DayOrganizerEntity sunday = new DayOrganizerEntity();
        sunday.setDayType(DayTypeOrganizerEntity.SUNDAY);
        sunday.getChoices().add(choice(MealOrganizer.ANYONE, "Café", "Céréales", "Gratin", "Féculant"));

        return organizerStudent;
    }

    private ChoiceOrganizerEntity choice(MealOrganizer mealOrganizer, String... labels) {
        RecipeCategoriesChoiceOrganizerEntity entity = new RecipeCategoriesChoiceOrganizerEntity();
        entity.setMeal(mealOrganizer);
        for(String label : labels) {
            IdLabelDTO idLabel = recipeExternalAdapter.findByLabel(label).get();
            entity.getRecipeCategoryIds().add(idLabel.getId());
        }
        return entity;
    }
    
}

package fr.on.mange.quoi.organizer.domain.model.choice;


import fr.on.mange.quoi.generic.facade.IdLabelDTO;
import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.Organizer;
import fr.on.mange.quoi.recipe.facade.adapter.RecipeExternalAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class ChoiceOrganizerTemplateStudentFactory implements ChoiceOrganizerTemplateFactory {

    @Autowired
    private RecipeExternalAdapter recipeExternalAdapter;

    public Organizer build() {

        //--------------------------------
        Organizer organizerStudent = new Organizer();
        organizerStudent.setLabel("template student");
        //--------------------------------

        organizerStudent.insert(createOrgaDay(organizerStudent, DayOfWeek.MONDAY));
        organizerStudent.insert(createOrgaDay(organizerStudent, DayOfWeek.TUESDAY));
        organizerStudent.insert(createOrgaDay(organizerStudent, DayOfWeek.WEDNESDAY));
        organizerStudent.insert(createOrgaDay(organizerStudent, DayOfWeek.THURSDAY));
        organizerStudent.insert(createOrgaDay(organizerStudent, DayOfWeek.FRIDAY));
        organizerStudent.insert(createOrgaDay(organizerStudent, DayOfWeek.SATURDAY));
        organizerStudent.insert(createOrgaDay(organizerStudent, DayOfWeek.SUNDAY));

        //----------------MONDAY-----------------------
        DayOrganizer monday = createOrgaDay(organizerStudent, DayOfWeek.MONDAY);
        monday.put(MealOrganizer.BREAK_FAST, categories("Céréales", "Café"));
        monday.put(MealOrganizer.SUPPER, categories("Gratin"));
        organizerStudent.insert(monday);


        //----------------TUESDAY-----------------------
        DayOrganizer tuesday = createOrgaDay(organizerStudent, DayOfWeek.TUESDAY);
        tuesday.put(MealOrganizer.BREAK_FAST, categories("Chocolat", "Café"));
        tuesday.put(MealOrganizer.SUPPER, categories("Burger"));
        organizerStudent.insert(tuesday);

        //----------------WEDNESDAY-----------------------
        DayOrganizer wednesday = createOrgaDay(organizerStudent, DayOfWeek.WEDNESDAY);
        wednesday.put(MealOrganizer.BREAK_FAST, categories("Yaourt", "Café"));
        wednesday.put(MealOrganizer.SUPPER, categories("Féculant", "Compote"));
        organizerStudent.insert(wednesday);

        //----------------THURSDAY-----------------------
        DayOrganizer thursday = createOrgaDay(organizerStudent, DayOfWeek.THURSDAY);
        thursday.put(MealOrganizer.BREAK_FAST, categories("Compote", "Café"));
        thursday.put(MealOrganizer.SUPPER, categories("Tarte/Pizza/Cake", "Bière"));
        organizerStudent.insert(thursday);

        //----------------FRIDAY-----------------------
        DayOrganizer friday = createOrgaDay(organizerStudent, DayOfWeek.FRIDAY);
        friday.put(MealOrganizer.BREAK_FAST, categories("Céréales", "Café"));
        friday.put(MealOrganizer.APERITIF, categories("Bière", "Chips"));
        friday.put(MealOrganizer.SUPPER, categories("Japonais"));
        organizerStudent.insert(friday);

        //----------------SATURDAY-----------------------
        DayOrganizer saturday = createOrgaDay(organizerStudent, DayOfWeek.SATURDAY);
        saturday.put(MealOrganizer.ANYONE, categories("Café"));
        saturday.put(MealOrganizer.ANYONE, categories("Féculant"));
        saturday.put(MealOrganizer.ANYONE, categories("Compote"));
        saturday.put(MealOrganizer.APERITIF, categories("Chips", "Bière", "Tarte/Pizza/Cake"));
        organizerStudent.insert(saturday);

        //----------------SUNDAY-----------------------
        DayOrganizer sunday = createOrgaDay(organizerStudent, DayOfWeek.SUNDAY);
        sunday.put(MealOrganizer.ANYONE, categories("Café"));
        sunday.put(MealOrganizer.ANYONE, categories("Céréales"));
        sunday.put(MealOrganizer.ANYONE, categories("Gratin"));
        sunday.put(MealOrganizer.ANYONE, categories("Féculant"));

        return organizerStudent;
    }

    private RecipeCategoriesChoiceOrganizer categories(String... labels) {
        RecipeCategoriesChoiceOrganizer categories = new RecipeCategoriesChoiceOrganizer();
        for(String label : labels) {
            IdLabelDTO idLabel = recipeExternalAdapter.findByLabel(label).get();
            categories.insert(new RecipeCategoryChoiceOrganizer(idLabel.getId(), idLabel.getLabel()));
        }
        return categories;
    }

    private DayOrganizer createOrgaDay(Organizer organizer, DayOfWeek dayType) {
        DayOrganizer day = new DayOrganizer(organizer, dayType);
        return day;
    }
}

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
public class ChoiceOrganizerTemplateFamilyFactory implements ChoiceOrganizerTemplateFactory {

    @Autowired
    private RecipeExternalAdapter recipeExternalAdapter;

    public Organizer build() {

        //--------------------------------
        Organizer organizerFamilial = new Organizer();
        organizerFamilial.setLabel("template family");
        //--------------------------------

        organizerFamilial.insert(createOrgaDay(organizerFamilial, DayOfWeek.MONDAY));
        organizerFamilial.insert(createOrgaDay(organizerFamilial, DayOfWeek.TUESDAY));
        organizerFamilial.insert(createOrgaDay(organizerFamilial, DayOfWeek.WEDNESDAY));
        organizerFamilial.insert(createOrgaDay(organizerFamilial, DayOfWeek.THURSDAY));
        organizerFamilial.insert(createOrgaDay(organizerFamilial, DayOfWeek.FRIDAY));
        organizerFamilial.insert(createOrgaDay(organizerFamilial, DayOfWeek.SATURDAY));
        organizerFamilial.insert(createOrgaDay(organizerFamilial, DayOfWeek.SUNDAY));

        //----------------MONDAY-----------------------
        DayOrganizer monday = createOrgaDay(organizerFamilial, DayOfWeek.MONDAY);
        monday.put(MealOrganizer.BREAK_FAST, categories("Compote"));
        monday.put(MealOrganizer.LUNCH, categories("Gratin", "Yaourt"));
        monday.put(MealOrganizer.SNACK, categories("Chocolat"));
        monday.put(MealOrganizer.SUPPER, categories("Féculant", "Yaourt"));
        organizerFamilial.insert(monday);

        //----------------TUESDAY-----------------------
        DayOrganizer tuesday = createOrgaDay(organizerFamilial, DayOfWeek.TUESDAY);
        tuesday.put(MealOrganizer.BREAK_FAST, categories("Céréales"));
        tuesday.put(MealOrganizer.LUNCH, categories("Tarte/Pizza/Cake"));
        tuesday.put(MealOrganizer.SNACK, categories("Yaourt"));
        tuesday.put(MealOrganizer.SUPPER, categories("Japonais"));
        organizerFamilial.insert(tuesday);

        //----------------WEDNESDAY-----------------------
        DayOrganizer wednesday = createOrgaDay(organizerFamilial, DayOfWeek.TUESDAY);
        wednesday.put(MealOrganizer.BREAK_FAST, categories("Yaourt", "Céréales"));
        wednesday.put(MealOrganizer.LUNCH, categories("Féculant"));
        wednesday.put(MealOrganizer.SNACK, categories("Compote"));
        wednesday.put(MealOrganizer.SUPPER, categories("Gratin", "Compote"));
        organizerFamilial.insert(wednesday);

        //----------------THURSDAY-----------------------
        DayOrganizer thursday = createOrgaDay(organizerFamilial, DayOfWeek.TUESDAY);
        thursday.put(MealOrganizer.BREAK_FAST, categories("Compote"));
        thursday.put(MealOrganizer.LUNCH, categories("Burger"));
        thursday.put(MealOrganizer.SNACK, categories("Chocolat"));
        thursday.put(MealOrganizer.SUPPER, categories("Soupe", "Yaourt"));
        organizerFamilial.insert(thursday);

        //----------------FRIDAY-----------------------
        DayOrganizer friday = createOrgaDay(organizerFamilial, DayOfWeek.TUESDAY);
        friday.put(MealOrganizer.BREAK_FAST, categories("Céréales"));
        friday.put(MealOrganizer.LUNCH, categories("Féculant"));
        friday.put(MealOrganizer.SNACK, categories("Yaourt"));
        friday.put(MealOrganizer.SUPPER, categories("Tarte/Pizza/Cake", "Bière"));
        organizerFamilial.insert(friday);

        //----------------SATURDAY-----------------------
        DayOrganizer saturday = createOrgaDay(organizerFamilial, DayOfWeek.TUESDAY);
        saturday.put(MealOrganizer.ANYONE, categories("Gratin"));
        saturday.put(MealOrganizer.ANYONE, categories("Féculant"));
        saturday.put(MealOrganizer.ANYONE, categories("Yaourt"));
        saturday.put(MealOrganizer.APERITIF, categories("Chips", "Bière"));
        organizerFamilial.insert(saturday);

        //----------------SUNDAY-----------------------
        DayOrganizer sunday = createOrgaDay(organizerFamilial, DayOfWeek.TUESDAY);
        sunday.put(MealOrganizer.ANYONE, categories("Tarte/Pizza/Cake"));
        sunday.put(MealOrganizer.ANYONE, categories("Gratin"));
        sunday.put(MealOrganizer.ANYONE, categories("Compote"));
        sunday.put(MealOrganizer.SNACK, categories("Chocolat", "Yaourt", "Compote"));
        organizerFamilial.insert(sunday);

        return organizerFamilial;
    }

    private RecipeCategoriesChoiceOrganizer categories(String... labels) {
        RecipeCategoriesChoiceOrganizer categories = new RecipeCategoriesChoiceOrganizer();
        for(String label:  labels) {
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

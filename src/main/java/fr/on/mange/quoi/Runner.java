package fr.on.mange.quoi;

import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoriesChoicePreparationOrganizer;
import fr.on.mange.quoi.organizer.persistence.entity.DayOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.DayTypeOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.RecipeCategoriesChoiceOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.repository.ChoiceOrganizerRepository;
import fr.on.mange.quoi.organizer.persistence.repository.DayOrganizerRepository;
import fr.on.mange.quoi.organizer.persistence.repository.OrganizerRepository;
import fr.on.mange.quoi.recipe.persistence.entity.RecipeCategoryEntity;
import fr.on.mange.quoi.recipe.persistence.repository.RecipeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
/*@EnableGlobalMethodSecurity(prePostEnabled = true)*/
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @Autowired
    private RecipeCategoryRepository recipeCategoryRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private DayOrganizerRepository dayOrganizerRepository;

    @Autowired
    private ChoiceOrganizerRepository choiceOrganizerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        if (organizerRepository.count() == 0) {
            init();
        }
    }

    private void init() {
        RecipeCategoryEntity gratin = new RecipeCategoryEntity();
        gratin.setLabel("Gratin");
        recipeCategoryRepository.save(gratin);

        RecipeCategoryEntity tartePizzaCake = new RecipeCategoryEntity();
        tartePizzaCake.setLabel("Tarte/Pizza/Cake");
        recipeCategoryRepository.save(tartePizzaCake);

        RecipeCategoryEntity compote = new RecipeCategoryEntity();
        compote.setLabel("Compote");
        recipeCategoryRepository.save(compote);

        RecipeCategoryEntity burger = new RecipeCategoryEntity();
        burger.setLabel("Burger");
        recipeCategoryRepository.save(burger);

        RecipeCategoryEntity biere = new RecipeCategoryEntity();
        biere.setLabel("Bière");
        recipeCategoryRepository.save(biere);

        RecipeCategoryEntity chips = new RecipeCategoryEntity();
        chips.setLabel("Chips");
        recipeCategoryRepository.save(chips);

        RecipeCategoryEntity feculant = new RecipeCategoryEntity();
        feculant.setLabel("Féculant");
        recipeCategoryRepository.save(feculant);

        RecipeCategoryEntity japonais = new RecipeCategoryEntity();
        japonais.setLabel("Japonais");
        recipeCategoryRepository.save(japonais);

        RecipeCategoryEntity soup = new RecipeCategoryEntity();
        soup.setLabel("Soupe");
        recipeCategoryRepository.save(soup);

        RecipeCategoryEntity yaourt = new RecipeCategoryEntity();
        yaourt.setLabel("Yaourt");
        recipeCategoryRepository.save(yaourt);

        RecipeCategoryEntity chocolat = new RecipeCategoryEntity();
        chocolat.setLabel("Chocolat");
        recipeCategoryRepository.save(chocolat);

        RecipeCategoryEntity cereales = new RecipeCategoryEntity();
        cereales.setLabel("Céréales");
        recipeCategoryRepository.save(cereales);

        RecipeCategoryEntity coffee = new RecipeCategoryEntity();
        coffee.setLabel("Café");
        recipeCategoryRepository.save(coffee);

        OrganizerEntity organizer = new OrganizerEntity();
        organizer.setLabel("Organisateur d'exemple");
        organizerRepository.save(organizer);


        createOrgaDayWithCategoryChoice(organizer, DayTypeOrganizerEntity.MONDAY, gratin);
        createOrgaDayWithCategoryChoice(organizer, DayTypeOrganizerEntity.TUESDAY, tartePizzaCake);

        DayOrganizerEntity wednesday = createOrgaDay(organizer, DayTypeOrganizerEntity.WEDNESDAY);

        RecipeCategoriesChoiceOrganizerEntity wednesdaySnackCompote = new RecipeCategoriesChoiceOrganizerEntity();
        wednesdaySnackCompote.setDay(wednesday);
        wednesdaySnackCompote.setMeal(MealOrganizer.SNACK);
        wednesdaySnackCompote.getRecipeCategoryIds().add(compote.getId());
        choiceOrganizerRepository.save(wednesdaySnackCompote);

        RecipeCategoriesChoiceOrganizerEntity wednesdaySupperBurger = new RecipeCategoriesChoiceOrganizerEntity();
        wednesdaySupperBurger.setDay(wednesday);
        wednesdaySupperBurger.setMeal(MealOrganizer.SUPPER);
        wednesdaySupperBurger.getRecipeCategoryIds().add(biere.getId());
        choiceOrganizerRepository.save(wednesdaySupperBurger);


        DayOrganizerEntity friday = createOrgaDay(organizer, DayTypeOrganizerEntity.FRIDAY);

        RecipeCategoriesChoiceOrganizerEntity fridayApero = new RecipeCategoriesChoiceOrganizerEntity();
        fridayApero.setDay(friday);
        fridayApero.setMeal(MealOrganizer.APERITIF);
        fridayApero.getRecipeCategoryIds().add(biere.getId());
        fridayApero.getRecipeCategoryIds().add(chips.getId());
        choiceOrganizerRepository.save(fridayApero);

        RecipeCategoriesChoiceOrganizerEntity fridaySupper = new RecipeCategoriesChoiceOrganizerEntity();
        fridaySupper.setDay(friday);
        fridaySupper.setMeal(MealOrganizer.SUPPER);
        fridaySupper.getRecipeCategoryIds().add(feculant.getId());
        choiceOrganizerRepository.save(fridaySupper);

        DayOrganizerEntity saturday = createOrgaDay(organizer, DayTypeOrganizerEntity.SATURDAY);

        RecipeCategoriesChoiceOrganizerEntity saturdayDeliveryJap = new RecipeCategoriesChoiceOrganizerEntity();
        saturdayDeliveryJap.setDay(saturday);
        saturdayDeliveryJap.setPreparation(RecipeCategoriesChoicePreparationOrganizer.TO_DELIVERY);
        saturdayDeliveryJap.setMeal(MealOrganizer.SUPPER);
        saturdayDeliveryJap.getRecipeCategoryIds().add(japonais.getId());
        choiceOrganizerRepository.save(saturdayDeliveryJap);

        DayOrganizerEntity sunday = createOrgaDay(organizer, DayTypeOrganizerEntity.SUNDAY);

        RecipeCategoriesChoiceOrganizerEntity sundayHomeMadeSoup = new RecipeCategoriesChoiceOrganizerEntity();
        sundayHomeMadeSoup.setDay(sunday);
        sundayHomeMadeSoup.setPreparation(RecipeCategoriesChoicePreparationOrganizer.TO_DO_RECIPE);
        sundayHomeMadeSoup.setMeal(MealOrganizer.SUPPER);
        sundayHomeMadeSoup.getRecipeCategoryIds().add(soup.getId());
        choiceOrganizerRepository.save(sundayHomeMadeSoup);

        DayOrganizerEntity noMatter = createOrgaDay(organizer, DayTypeOrganizerEntity.NO_MATTER);

        RecipeCategoriesChoiceOrganizerEntity yaourtNoMatter = new RecipeCategoriesChoiceOrganizerEntity();
        yaourtNoMatter.setDay(noMatter);
        yaourtNoMatter.getRecipeCategoryIds().add(coffee.getId());
        choiceOrganizerRepository.save(yaourtNoMatter);
    }

    private void createOrgaDayWithCategoryChoice(OrganizerEntity organizer, DayTypeOrganizerEntity dayType, RecipeCategoryEntity category) {
        DayOrganizerEntity day = createOrgaDay(organizer, dayType);

        RecipeCategoriesChoiceOrganizerEntity choice = new RecipeCategoriesChoiceOrganizerEntity();
        choice.setDay(day);
        choice.setMeal(MealOrganizer.ANYONE);
        choice.getRecipeCategoryIds().add(category.getId());
        choiceOrganizerRepository.save(choice);
    }

    private DayOrganizerEntity createOrgaDay(OrganizerEntity organizer, DayTypeOrganizerEntity dayType) {
        DayOrganizerEntity day = new DayOrganizerEntity();
        day.setOrganizer(organizer);
        day.setDayType(dayType);
        dayOrganizerRepository.save(day);
        return day;
    }

}

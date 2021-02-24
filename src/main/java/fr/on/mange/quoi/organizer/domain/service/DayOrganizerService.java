package fr.on.mange.quoi.organizer.domain.service;

import fr.on.mange.quoi.organizer.domain.model.MealOrganizer;
import fr.on.mange.quoi.organizer.facade.dto.DayOrganizerDTO;
import fr.on.mange.quoi.organizer.facade.dto.MealOrganizerDTO;
import fr.on.mange.quoi.organizer.persistence.entity.ChoiceOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.DayOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.repository.DayOrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DayOrganizerService {

    @Autowired
    DayOrganizerRepository dayOrganizerRepository;

    public List<MealOrganizer> createListLabel() {

        List<MealOrganizer> mealOrganizerList = List.of(MealOrganizer.LUNCH,
                MealOrganizer.APERITIF,
                MealOrganizer.SNACK,
                MealOrganizer.BREAK_FAST,
                MealOrganizer.SUPPER);
        return mealOrganizerList;
    }

    public List<MealOrganizer> createNewListLabel (DayOrganizerDTO dayOrganizerDTO) {

        List <MealOrganizer> empty = new ArrayList<>();
        List<MealOrganizer> mealOrganizerList = new ArrayList<>(List.of(MealOrganizer.LUNCH,
                MealOrganizer.APERITIF,
                MealOrganizer.SNACK,
                MealOrganizer.BREAK_FAST,
                MealOrganizer.SUPPER));

        System.out.println("avant");
        for (MealOrganizer test : mealOrganizerList) {
            System.out.println(test.labelFr());
        }

      for (int i = 0; i < mealOrganizerList.size(); i++ ) {
          for (MealOrganizerDTO listeRecu : dayOrganizerDTO.getMeals()) {
              if (mealOrganizerList.get(i).labelFr().equals(listeRecu.getTypeLabel())) {
                  mealOrganizerList.remove(i);
              }
          }

      }

        System.out.println("en sortie :");
        System.out.println("get 0" + mealOrganizerList.get(0));
        for (MealOrganizer test : mealOrganizerList) {
            System.out.println(test.labelFr());
        }

        return mealOrganizerList;
    }


}

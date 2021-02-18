package fr.on.mange.quoi.organizer.domain.service;

import fr.on.mange.quoi.generic.facade.IdLabelDTO;
import fr.on.mange.quoi.organizer.domain.model.choice.RecipeCategoryChoiceOrganizer;
import fr.on.mange.quoi.organizer.persistence.entity.RecipeCategoriesChoiceOrganizerEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeCategoryChoiceOrganizerWrapper {

    public List<RecipeCategoryChoiceOrganizer> fromDTOS(List<IdLabelDTO> dtos) {
        List<RecipeCategoryChoiceOrganizer> listCategoriesModel = new ArrayList<>();
        for (IdLabelDTO dto : dtos) {
            listCategoriesModel.add(fromDTO(dto));
        }
        return listCategoriesModel;
    }

    public RecipeCategoryChoiceOrganizer fromDTO(IdLabelDTO dto) {
        RecipeCategoryChoiceOrganizer model = new RecipeCategoryChoiceOrganizer(dto.getId(), dto.getLabel());
        return model;
    }

    /*
    public RecipeCategoriesChoiceOrganizerEntity toEntity(RecipeCategoryChoiceOrganizer model) {
        RecipeCategoriesChoiceOrganizerEntity entity = new RecipeCategoriesChoiceOrganizerEntity();
        entity.getRecipeCategoryIds().add(model.optRecipeCategoryId().get());
        return entity;
    }

    public RecipeCategoryChoiceOrganizer fromEntity(RecipeCategoriesChoiceOrganizerEntity entity) {
        RecipeCategoryChoiceOrganizer model = new RecipeCategoryChoiceOrganizer(entity.getId());
        return model;
    }
    */

}



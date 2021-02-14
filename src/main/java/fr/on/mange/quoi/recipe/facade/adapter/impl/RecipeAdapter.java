package fr.on.mange.quoi.recipe.facade.adapter.impl;

import fr.on.mange.quoi.generic.facade.IdLabelDTO;
import fr.on.mange.quoi.recipe.domain.model.RecipeDish;
import fr.on.mange.quoi.recipe.facade.adapter.RecipeExternalAdapter;
import fr.on.mange.quoi.recipe.persistence.repository.RecipeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RecipeAdapter implements RecipeExternalAdapter {

    @Autowired
    private RecipeCategoryRepository categoryRepository;

    @Override
    public Optional<IdLabelDTO> findDishById(String dishId) {
        return Optional.empty();
    }

    @Override
    public Optional<IdLabelDTO> findCategoryById(String categoryId) {
        return categoryRepository
                .findById(categoryId)
                .map(category -> new IdLabelDTO(category.getId(), category.getLabel()));
    }
}

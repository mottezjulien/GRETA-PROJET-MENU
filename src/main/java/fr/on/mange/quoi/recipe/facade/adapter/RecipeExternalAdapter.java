package fr.on.mange.quoi.recipe.facade.adapter;

import fr.on.mange.quoi.generic.facade.IdLabelDTO;

import java.util.Optional;

public interface RecipeExternalAdapter {

    Optional<IdLabelDTO> findDishById(String dishId);

    Optional<IdLabelDTO> findCategoryById(String categoryId);
}

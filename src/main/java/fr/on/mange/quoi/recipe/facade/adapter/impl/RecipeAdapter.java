package fr.on.mange.quoi.recipe.facade.adapter.impl;

import fr.on.mange.quoi.generic.facade.IdLabelDTO;
import fr.on.mange.quoi.recipe.facade.adapter.RecipeExternalAdapter;
import fr.on.mange.quoi.recipe.persistence.repository.RecipeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<IdLabelDTO> findAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(category -> new IdLabelDTO(category.getId(), category.getLabel()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<IdLabelDTO> findByLabel(String label) {
        return  categoryRepository
                .findByLabel(label)
                .map(category -> new IdLabelDTO(category.getId(), category.getLabel()));
    }
}

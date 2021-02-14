package fr.on.mange.quoi.recipe.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecipeDish {

    private Optional<String> optId =  Optional.empty();

    private String label;

    private String instruction;

    private List<RecipeCategory> catagories = new ArrayList<>();

    private List<RecipeIngredient> ingredients = new ArrayList<>();

}

package fr.on.mange.quoi.recipe.persistence.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "RECIPE_DISH")
public class RecipeDishEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String label;

    private String instruction;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_dish_category",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<RecipeCategoryEntity> catagories = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_dish_ingredient",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<RecipeIngredientEntity> ingredients = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<RecipeCategoryEntity> getCatagories() {
        return catagories;
    }

    public void setCatagories(List<RecipeCategoryEntity> catagories) {
        this.catagories = catagories;
    }

    public List<RecipeIngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }
}

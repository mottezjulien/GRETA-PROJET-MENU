package fr.on.mange.quoi.recipe.persistence.repository;

import fr.on.mange.quoi.recipe.persistence.entity.RecipeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategoryEntity, String> {

}

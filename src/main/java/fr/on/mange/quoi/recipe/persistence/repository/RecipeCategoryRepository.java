package fr.on.mange.quoi.recipe.persistence.repository;

import fr.on.mange.quoi.generic.facade.IdLabelDTO;
import fr.on.mange.quoi.recipe.persistence.entity.RecipeCategoryEntity;
import fr.on.mange.quoi.user.persistance.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategoryEntity, String> {


    Optional<IdLabelDTO> findByLabel(String label);

}

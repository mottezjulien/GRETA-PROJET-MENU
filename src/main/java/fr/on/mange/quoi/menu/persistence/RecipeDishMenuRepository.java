package fr.on.mange.quoi.menu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeDishMenuRepository extends JpaRepository<RecipeDishMenuEntity, String> {
}

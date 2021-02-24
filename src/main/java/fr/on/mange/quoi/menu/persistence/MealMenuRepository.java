package fr.on.mange.quoi.menu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealMenuRepository extends JpaRepository<MealMenuEntity, String> {
}

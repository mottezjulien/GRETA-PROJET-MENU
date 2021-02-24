package fr.on.mange.quoi.menu.persistence;

import fr.on.mange.quoi.menu.persistence.DayMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayMenuRepository extends JpaRepository<DayMenuEntity, String> {
}

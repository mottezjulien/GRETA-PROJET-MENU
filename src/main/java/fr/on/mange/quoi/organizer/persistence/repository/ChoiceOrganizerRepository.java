package fr.on.mange.quoi.organizer.persistence.repository;

import fr.on.mange.quoi.organizer.facade.AddChoiceDTORequest;
import fr.on.mange.quoi.organizer.persistence.entity.ChoiceOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.RecipeCategoriesChoiceOrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ChoiceOrganizerRepository extends JpaRepository<ChoiceOrganizerEntity, String> {

    ChoiceOrganizerEntity findByDayId(String dayId);

    @Modifying
    @Query("DELETE FROM ChoiceOrganizerEntity c WHERE c.id = ?1")
    void deleteById(String id);
}

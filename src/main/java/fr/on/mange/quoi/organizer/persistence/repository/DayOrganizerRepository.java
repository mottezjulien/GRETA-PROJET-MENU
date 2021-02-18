package fr.on.mange.quoi.organizer.persistence.repository;

import fr.on.mange.quoi.organizer.domain.model.DayOrganizer;
import fr.on.mange.quoi.organizer.persistence.entity.DayOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DayOrganizerRepository extends JpaRepository<DayOrganizerEntity, String> {

    @Query("SELECT d" +
            " FROM DayOrganizerEntity d" +
            " LEFT JOIN FETCH d.choices c" +
            " WHERE d.id = :dayId")
    Optional<DayOrganizerEntity> findByIdFetchAll(String dayId);

}

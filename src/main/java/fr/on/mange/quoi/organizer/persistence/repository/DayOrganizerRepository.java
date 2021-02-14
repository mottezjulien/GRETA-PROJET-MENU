package fr.on.mange.quoi.organizer.persistence.repository;

import fr.on.mange.quoi.organizer.persistence.entity.DayOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOrganizerRepository extends JpaRepository<DayOrganizerEntity, String> {

}

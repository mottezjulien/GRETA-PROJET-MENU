package fr.on.mange.quoi.organizer.persistence.repository;

import fr.on.mange.quoi.organizer.facade.AddChoiceDTORequest;
import fr.on.mange.quoi.organizer.persistence.entity.ChoiceOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ChoiceOrganizerRepository extends JpaRepository<ChoiceOrganizerEntity, String> {


}

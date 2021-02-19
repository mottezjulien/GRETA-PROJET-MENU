package fr.on.mange.quoi.organizer.persistence.repository;

import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizerRepository extends JpaRepository<OrganizerEntity, String> {

    @Query("SELECT o" +
            " FROM OrganizerEntity o" +
            " LEFT JOIN FETCH o.days d" +
            " LEFT JOIN FETCH d.choices c")
    List<OrganizerEntity> findAllFetchAll();
    List<OrganizerEntity> findAllByUserId(String userId);

    Optional<OrganizerEntity> findByLabel(String label);

}

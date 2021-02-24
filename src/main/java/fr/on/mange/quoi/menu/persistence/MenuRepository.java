package fr.on.mange.quoi.menu.persistence;

import fr.on.mange.quoi.menu.persistence.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, String> {

    @Query("SELECT m" +
            " FROM MenuEntity m" +
            " LEFT JOIN FETCH m.days d" +
            " LEFT JOIN FETCH d.meals e")
    List<MenuEntity> findAllFetchAll();

}

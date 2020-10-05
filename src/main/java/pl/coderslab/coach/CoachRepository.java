package pl.coderslab.coach;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach, Long> {

    @Query("select c from Coach c where c.id = :id")
    Coach findCoachById(@Param("id") Long id);

    @Query("select p from Coach p join p.clubs c where c.id = :id")
    List<Coach> findCoachByClub(@Param("id") Long id);
}

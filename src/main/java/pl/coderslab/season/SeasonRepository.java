package pl.coderslab.season;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    @Query(value = "select * from seasons s order by s.start_year desc limit 1", nativeQuery = true)
    Season findLast();


}

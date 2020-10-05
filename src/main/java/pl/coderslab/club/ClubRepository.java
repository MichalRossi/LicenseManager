package pl.coderslab.club;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClubRepository extends JpaRepository<Club, Long> {

    @Query("select c from Club c where c.id = :id")
    Club findClubById(@Param("id") Long id);

}

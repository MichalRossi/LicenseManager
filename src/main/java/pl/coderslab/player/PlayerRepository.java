package pl.coderslab.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select p from Player p where p.id = :id")
    Player findPlayerById(@Param("id") Long id);


    @Query("select p from Player p join p.clubs c where c.id = :id")
    List<Player> findPlayerByClub(@Param("id") Long id);

    @Query("select p from Player p join p.seasons s where s.id = :id")
    List<Player> findPlayerBySeason(Long id);
}

package com.ct5106.swishStats.repository;

import com.ct5106.swishStats.domain.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// grr!
public interface PlayerRepository extends CrudRepository<Player, Long> {
    // fetch players by position
    List<Player> findPlayerByPosition(String position);

    // fetch players by age range
    @Query("select p from Player p where p.age between ?1 and ?2")
    List<Player> findPlayersByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

    // fetch players by rating range
    @Query("select p from Player p where p.playerRating between ?1 and ?2")
    List<Player> findPlayersByRatingRange(@Param("minRating") int minRating, @Param("maxRating") int maxRating);
}

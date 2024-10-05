package com.ct5106.swishStats.repository;

import com.ct5106.swishStats.domain.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long>
{
    List<Team> findByTeamName(String teamName);

    // fetch teams by ratings range
    @Query("select t from Team t where t.teamRating between ?1 and ?2")
    List<Team> findByTeamRatingRange(@Param("minRating") int minRating, @Param("maxRating") int maxRating);
}


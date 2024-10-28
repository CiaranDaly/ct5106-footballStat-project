package com.ct5106.swishStats.repository;


import java.util.List;

import com.ct5106.swishStats.domain.League;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource
public interface LeagueRepository extends CrudRepository<League, Long>
{

    List<League> findByName (String name);
    
//    @Query("select l from League l join l.teams t where t.teamName like %:team%")
//    List<League> findByTeamContaining(@Param("team") String team);
@Query("select distinct l from League l join l.teams t where lower(t.teamName) like lower(concat('%', :team, '%'))")
List<League> findByTeamContaining(@Param("team") String team);
}
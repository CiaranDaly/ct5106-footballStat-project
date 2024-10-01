package com.ct5106.swishStats.repository;


import java.util.List;

import com.ct5106.swishStats.domain.League;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="leagues")
public interface LeagueRepository extends CrudRepository<League, Long>
{

    List<League> findByName (String name);

}
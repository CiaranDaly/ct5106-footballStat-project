package com.ct5106.swishStats.repository;


import java.util.List;

import com.ct5106.swishStats.domain.Conference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="conferences")
public interface ConferenceRepository extends CrudRepository<Conference, Long>
{

    List<Conference> findByName (String name);

}
package com.ct5106.swishStats.repository;

import com.ct5106.swishStats.domain.League;
import com.ct5106.swishStats.domain.Manager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin
@RepositoryRestResource
public interface ManagerRepository extends CrudRepository<Manager, Long>
{
    List<Manager> findManagersByNationality (String nationality);
    // fetch managers by ratings range
    @Query("select m from Manager m where m.managerRating between ?1 and ?2")
    List<Manager> findManagersByRatingRange(@Param("minRating") int minRating, @Param("maxRating") int maxRating);

}
